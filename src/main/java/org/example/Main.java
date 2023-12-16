package org.example;

import org.example.db.DataBase;
import org.example.dto.Car;
import org.example.dto.Profile;
import org.example.dto.ServiceType;
import org.example.enums.Status;
import org.example.service.CarService;
import org.example.service.ProfileService;
import org.example.service.ProvidedAffairService;
import org.example.service.ServiceTypeService;
import org.example.utils.ScannerUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ScannerUtils scannerUtils=new ScannerUtils();
    static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    static DataBase dataBase = context.getBean(DataBase.class);
    static ProfileService profileService = context.getBean(ProfileService.class);
    static CarService carService = context.getBean(CarService.class);
    static ServiceTypeService servicType = context.getBean(ServiceTypeService.class);
    static ProvidedAffairService affairService = context.getBean(ProvidedAffairService.class);

    public static void main(String[] args) {

        dataBase.creatTableProfile();
        dataBase.creatTableCar();
        dataBase.creatTableServiceType();
        dataBase.creatTableAffair();
        do {
            System.out.println();
            System.out.println("""
                    1.CREATED PROFILE
                    2.UPDATE PROFILE
                    3.GET BY ID PROFILE
                    4.SHOW PROFILE LIST
                    5.SEARCH PROFILE
                    6.CRETE CAR
                    7.FIND BY CAR NUMBER
                    8.FIND BY CAR ID
                    9.SHOW CAR LIST
                    10.CREAT SERVICE TYPE
                    11.FIND SERVICE TYPE BY ID
                    12.ALL ACTIVE SERVICE TYPE
                    13.CHANGE SERVICE TYPE STATUS
                    14.SHOW SERVICE TYPE LIST
                    15.CREAT AFFAIR SERVICE
                    16.SHOW BY CAR ID IN AFFAIR SERVICE
                    17.SHOW AFFAIR SERVICE
                    0.EXIST
                    """);
            System.out.println("Chose action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0 -> {
                    return;
                }
                case 1 -> createdProfile();
                case 2 -> updateprofile();
                case 3 -> getById();
                case 4 -> profileService.printProfileList().forEach(System.out::println);
                case 5 -> serch();
                case 6 -> cretedCar();
                case 7 -> findByCardNumber();
                case 8 -> findByCarid();
                case 9 -> carService.printAll().forEach(System.out::println);
                case 10 -> creatService();
                case 11 -> findserviceByid();
                case 12 -> allActiveService(servicType);
                case 13 -> changeServiceStatus();
                case 14 -> showServiceTypeList();
                case 15 -> createAffairService();
                case 16 -> listByCarIdInAffairService();
                case 17 -> affairService.all().forEach(System.out::println);
                default -> System.out.println("Wrong action!!!");
            }
        } while (true);


    }

    private static void showServiceTypeList() {
        List<ServiceType> all = servicType.all();
        if (all.isEmpty()) {
            System.out.println("List empty!!!");
        }
        all.forEach(System.out::println);
    }

    private static void listByCarIdInAffairService() {
        System.out.println("Enter car id: ");
        int carId = scannerUtils.nextInt("Enter car id: ");
        affairService.listByCarId(carId).forEach(System.out::println);
    }

    private static void createAffairService() {
        Integer profileId = scannerUtils.nextInt("Enter profile id:");
        Integer carId = scannerUtils.nextInt("Enter car id:");
        Integer serviceTypeId = scannerUtils.nextInt("Enter service type id:");

        affairService.create(profileId, carId, serviceTypeId);
    }

    private static void changeServiceStatus() {
        Integer serviceId = scannerUtils.nextInt("Enter service id:");
        String newServiceStatus = scannerUtils.nextLine("Enter service status(ACTIVE OR  NOT_ACTIVE): ");

        servicType.changeStatus(serviceId, newServiceStatus);
    }

    private static void allActiveService(ServiceTypeService servicType) {
        List<ServiceType> allActive = servicType.getAllActive();
        if (allActive != null) {
            allActive.forEach(System.out::println);
        } else {
            System.out.println("not found!!!");
        }
    }

    private static void findserviceByid() {
        Integer serviceId = scannerUtils.nextInt("Enter id:");
        scanner.nextLine();

        ServiceType byId = servicType.getById(serviceId);
        if (byId != null) {
            System.out.println(byId);
        } else {
            System.out.println("not found!!!");
        }
    }

    private static void creatService() {
        String newServiceStatus = scannerUtils.nextLine("Service name: ");
        double price = scannerUtils.nextDouble("Service price: ");
        double personTage = scannerUtils.nextDouble("Person Tage: ");

        servicType.create(newServiceStatus, price, personTage, LocalDate.now(), Status.ACTIVE.name());
    }

    private static void findByCarid() {
        Integer id = scannerUtils.nextInt("Enter card id: ");

        Car byCarId = carService.findById(id);
        if (byCarId != null) {
            System.out.println(byCarId);
        } else {
            System.out.println("not found!!!");
        }
    }

    private static void findByCardNumber() {
        String number = scannerUtils.nextLine("Enter card number: ");

        Car byCarNumber = carService.findByCarNumber(number);
        if (byCarNumber != null) {
            System.out.println(byCarNumber);
        } else {
            System.out.println("not found!!!");
        }
    }

    private static void cretedCar() {
        Car car = new Car();
        String number = scannerUtils.nextLine("Enter card number: ");
        String model  = scannerUtils.nextLine("Enter card model: ");
        String color  = scannerUtils.nextLine("Enter card color: ");

        car.setNumber(number);
        car.setModel(model);
        car.setColor(color);
        car.setCreatedData(LocalDate.now());
        carService.create(car);
    }

    private static void serch() {
        String searchingWord  = scannerUtils.nextLine("Enter searching word: ");

        Profile search = profileService.search(searchingWord);
        if (search != null) {
            System.out.println(search);
        } else {
            System.out.println("not found!!!");
        }
    }

    private static void getById() {
        Integer id  = scannerUtils.nextInt("Enter id: ");
        System.out.println(profileService.getById(id));
    }

    private static void updateprofile() {
        Integer updateId  = scannerUtils.nextInt("Enter id: ");
        String updateName = scannerUtils.nextLine("Enter name: ");
        String updateSurname = scannerUtils.nextLine("Enter surname: ");

        System.out.println(profileService.update(updateId, updateName, updateSurname));
    }

    private static void createdProfile() {
        Profile profile = new Profile();
        String name = scannerUtils.nextLine("Enter name: ");
        String surname = scannerUtils.nextLine("Enter surname: ");
        String phone = scannerUtils.nextLine("Enter phone: ");

        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setCreatedData(LocalDate.now());

        profileService.create(profile);
    }
}