package org.example.lesson2.tests;

public class TestRunnerDemo {

  // private никому не видно
  // default (package-private) внутри пакета
  // protected внутри пакета + наследники
  // public всем

  public static void main(String[] args) {
    TestRunner.run(TestRunnerDemo.class);
  }


  @BeforeAll
  void beforeAll1(){
    System.out.println("BeforeAll - 1");
  }
  @BeforeAll
  void beforeAll2(){
    System.out.println("BeforeAll - 2");
  }

  @BeforeEach
  void beforeEach1(){
    System.out.println("BeforeEach - 1");
  }
  @BeforeEach
  void beforeEach2(){
    System.out.println("BeforeEach - 2");
  }

  @AfterEach
  void afterEach1(){
    System.out.println("AfterEach - 1");
  }

  @AfterEach
  void afterEach2(){
    System.out.println("AfterEach - 2");
  }

  @AfterAll
  void afterAll1(){
    System.out.println("AfterAll - 1");
  }
  @AfterAll
  void afterAll2(){
    System.out.println("AfterAll - 2");
  }


  @Test
  private void test1() {
    System.out.println("test1");
  }

 @Test
  void test2() {
    System.out.println("test2");
  }

  @Test
  void test3() {
    System.out.println("test3");
  }

}
