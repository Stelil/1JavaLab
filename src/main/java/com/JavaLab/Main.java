package com.JavaLab;

import com.JavaLab.checkers.CheckerBrd;
import com.JavaLab.checkers.CheckerName;
import com.JavaLab.checkers.CheckerSex;
import com.JavaLab.comparator.ComparatorBrd;
import com.JavaLab.comparator.ComparatorName;
import com.JavaLab.comparator.ComparatorSex;
import com.JavaLab.handler.MyHandler;
import com.JavaLab.injectors.Injector;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import java.io.File;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Класс для открывания консоли пользователю, выбора из списка действий
 *
 * @author Воротников Дмитрий
 */
public class Main {
    /**
     * статическая переменная котроая определяет класс Repository
     */
    static Repository repository;

    private static final Logger logger = Logger.getLogger(Main.class);

    /**
     * главный метод для запусka
     *
     * @param args
     */
    public static void main(String[] args) {

        repository = new Injector().inject(new Repository());

        addPersons();

        boolean bool = true;
        while (bool) {
            System.out.println("Возможные действия : \n " +
                    "1. Добавить человека \n " +
                    "2. Показать весь список \n " +
                    "3. Удалить человека из списка по id \n " +
                    "4. Показать человека по id \n " +
                    "5. Отсортировать репозиторий \n " +
                    "6. Поиск в репозитории \n " +
                    "7. Выход \n " +
                    "8. Выгрузка в XML с помощью DOM \n " +
                    "9. Загрузка из XML с помощью DOM \n " +
                    "10. Загрузка из XML с помощью SAX \n " +
                    "11. Выгрузка в XML с помощью JAXB \n " +
                    "12. Загрузка из XML с помощью JAXB \n ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            logger.info("select item number : " + number);
            switch (number) {
                case 1:
                    newPerson();
                    viewPersons();
                    break;
                case 2:
                    viewPersons();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    viewPerson();
                    break;
                case 5:
                    sort();
                    break;
                case 6:
                    SearchValue();
                    break;
                case 7:
                    bool = false;
                    break;
                case 8:
                    dom();
                    break;
                case 9:
                    parseDom();
                    break;
                case 10:
                    parseSax();
                    break;
                case 11:
                    jaxb();
                    break;
                case 12:
                    parseJaxb();
                    break;
            }
        }
    }

    static void dom() {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);

            for (int h = 0; repository.getLength() > h; h++) {
                Element element = doc.createElement("student");
                rootElement.appendChild(element);

                Element child = doc.createElement("id");
                child.appendChild(doc.createTextNode(String.valueOf(repository.getHumanIndex(h).getId())));
                element.appendChild(child);
                child = doc.createElement("Name");
                child.appendChild(doc.createTextNode(String.valueOf(repository.getHumanIndex(h).getName())));
                element.appendChild(child);
                child = doc.createElement("Brd");
                child.appendChild(doc.createTextNode(String.valueOf(repository.getHumanIndex(h).getBrd())));
                element.appendChild(child);
                child = doc.createElement("Sex");
                child.appendChild(doc.createTextNode(String.valueOf(repository.getHumanIndex(h).getSex())));
                element.appendChild(child);
                child = doc.createElement("Age");
                child.appendChild(doc.createTextNode(String.valueOf(repository.getHumanIndex(h).getAge())));
                element.appendChild(child);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter("dom.xml", false));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void parseDom() {
        try {
            File inputFile = new File("dom.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("student");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    LocalDate inputDate = LocalDate.parse(eElement
                            .getElementsByTagName("Brd")
                            .item(0)
                            .getTextContent(), DateTimeFormat.forPattern("yyyy-mm-dd"));

                    Human h = new Human(eElement
                            .getElementsByTagName("Name")
                            .item(0)
                            .getTextContent(), inputDate, eElement
                            .getElementsByTagName("Sex")
                            .item(0)
                            .getTextContent());
                    repository.insert(h);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void parseSax() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File("dom.xml"), handler);
            List<Human> empList = handler.getEmpList();
            for (Human emp : empList) {
                Human human = new Human(emp.getName(), emp.getBrd(), emp.getSex());
                repository.insert(human);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void jaxb() {
        String fileName = "jaxb.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Human.class, Students.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Students students = new Students();
            for (int i = 0; repository.getLength() > i; i++) {
                Human human = new Human();
                human.setId(repository.getHumanIndex(i).getId());
                human.setName(repository.getHumanIndex(i).getName());
                human.setBrd(repository.getHumanIndex(i).getBrd());
                human.setSex(repository.getHumanIndex(i).getSex());
                students.student.add(human);

            }
            marshaller.marshal(students, new File(fileName));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    static void parseJaxb() {

    }


    /**
     * Создание стандартных людей
     */
    static void addPersons() {

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        inputDate = LocalDate.parse("01.10.2001", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("dima", inputDate, "male");
        repository.insert(h);

        inputDate = LocalDate.parse("01.02.2000", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("tom", inputDate, "male");
        repository.insert(h);

        inputDate = LocalDate.parse("25.05.1999", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("kate", inputDate, "female");
        repository.insert(h);

        inputDate = LocalDate.parse("11.11.1995", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("dima", inputDate, "male");
        repository.insert(h);

        viewPersons();

    }

    /**
     * Создание нового человека
     */
    private static void newPerson() {
        System.out.println("Введите имя:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        logger.info("new man, name: " + name);

        System.out.println("Введите дату рождения (dd.mm.yyyy):");
        scanner = new Scanner(System.in);
        String brd = scanner.nextLine();

        DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
        LocalDate inputDate;

        try {
            inputDate = LocalDate.parse(brd, dateFormat);
        } catch (Throwable t) {
            System.out.println("Неверный формат даты!");
            return;
        }

        logger.info("new man, brd: " + brd);

        System.out.println("Введите пол (female или male):");
        scanner = new Scanner(System.in);
        String sex = scanner.nextLine();

        logger.info("new man, sex: " + sex);

        Human human = new Human(name, inputDate, sex);
        repository.insert(human);

    }

    /**
     * Просмотр всех людей в репозитории
     */
    private static void viewPersons() {
        int length = repository.getLength();
        if (length == 0) {
            System.out.println("пусто");
            logger.info("an empty repository");
        } else {
            for (int i = 0; length > i; i++) {
                System.out.println(" id = " + repository.getHumanIndex(i).getId() +
                        " Полное имя = " + repository.getHumanIndex(i).getName() +
                        " Дата рождения = " + repository.getHumanIndex(i).getBrd() +
                        " Возраст " + repository.getHumanIndex(i).getAge() +
                        " Пол = " + repository.getHumanIndex(i).getSex());
            }
        }

    }

    /**
     * Удаления человека из репозитория по id
     */
    private static void deletePerson() {
        System.out.println("Введите id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        logger.info("id to delete a person number : " + id);
        repository.delete(id);
    }

    /**
     * Просмотр человека из репозитория по id
     */
    private static void viewPerson() {
        System.out.println("Введите id:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Human human = repository.getHumanId(id);

        DateTime dateTime = DateTime.now();
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd.MM.yyyy");
        System.out.println(dtfOut.print(dateTime));

        System.out.println(" id = " + human.getId() +
                " Полное имя " + human.getName() +
                " Дата рождения " + human.getBrd() +
                " возраст " + human.getAge() +
                " Пол " + human.getSex());

    }

    /**
     * Вызов сортировки
     */
    public static void sort() {
        System.out.println("Отсортирoвать по : \n " +
                "1. Имени \n " +
                "2. Году рождения \n " +
                "3. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                logger.info("sorting by name is selected");
                repository.sortBy(new ComparatorName());
                viewPersons();
                break;
            case 2:
                logger.info("sorting by brd is selected");
                repository.sortBy(new ComparatorBrd());
                viewPersons();
                break;
            case 3:
                logger.info("sorting by sex is selected");
                repository.sortBy(new ComparatorSex());
                viewPersons();
                break;
        }
    }

    /**
     * Поиск
     */
    private static void SearchValue() {
        System.out.println("Поиск по : \n " +
                "1. Имени \n " +
                "2. Году рождения \n " +
                "3. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        Human[] forSearch = new Human[0];
        switch (number) {
            case 1:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                String value = scanner.next();
                forSearch = repository.searchBy(new CheckerName(), value);
                logger.info("selected search by name with the value of :" + value);
                break;
            case 2:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                value = scanner.next();
                forSearch = repository.searchBy(new CheckerBrd(), value);
                logger.info("selected search by brd with the value of :" + value);
                break;
            case 3:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                value = scanner.next();
                forSearch = repository.searchBy(new CheckerSex(), value);
                logger.info("selected search by sex with the value of :" + value);
                break;
        }

        for (int i = 0; forSearch.length > i; i++) {
            System.out.println(" id = " + forSearch[i].getId() +
                    " Полное имя " + forSearch[i].getName() +
                    " Дата рождения " + forSearch[i].getBrd() +
                    " возраст " + forSearch[i].getAge() +
                    " Пол " + forSearch[i].getSex());
        }
    }

}