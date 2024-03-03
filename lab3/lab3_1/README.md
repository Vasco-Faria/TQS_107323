# Questions

## Identify a couple of examples that use AssertJ expressive methods chaining.

    assertThat(found).isEqualTo(alex);
    assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail());
    assertThat(doesEmployeeExist).isFalse();


## Identify an example in which you mock the behavior of the repository (and avoid involving a database).

@BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
    
    
## What is the difference between standard @Mock and @MockBean?

    @Mock is for creating mock objects in regular Java tests.
    
    @MockBean is for creating mock objects specifically in Spring tests to replace real beans with mocks.
    

## What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

    The file "application-integrationtest.properties" is used to provide environment-specific configuration properties for integration testing.
    It should be used on Testing Scenarios and configuration.


## the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

    The test C don`t involve the usage of a database, unlike tests D and E.
    The test C use @WebMvcTest and the others tests don`t.
    Both D and E leverage @SpringBootTest.
    Test E use TestRestTemplate, and test D  use MockMVC to make integration tests.
