# unit test

- method를 test하는 방법
- necessary!!
- lower cost of mistakes
- Junit
  - standard for Java
- can combine with Mockito for more complicated projects

수업에 필요한 파일을 다운 받은 후에 테스트하고 싶은 클래스파일에서 오른쪽 마우스 클릭을 하고, New에서 Junit Test Case를 클릭한다.
source folder명을 main에서 test로 바꿔준다.

- 우리는 test를 하고싶은 것이기에 main 폴더에서 test를 진행하지 않으므로, directory를 test로 변경한 것이다.
그리고 source folder 위에 있는 New Junit Jupiter test를 클릭해준다.

이렇게 path까지 추가해주면, 저절로 test폴더가 만들어지면서 test 파일에 생성된다.

- 초기 상태는 test 메소드 안에 fail이 있으므로 무조건 fail이 뜨지만, 이 구문을 지운다면 코드가 잘 돌아가는 것을 확인할 수 있다.

test 메소드 안에 assertTru()를 사용해서 확인하고 싶은 것을 ()안에 넣는다. 그것이 참이라면 test는 성황리에 끝날 것이다.

- test 메소드를 가독성을 높이기 위해 문장으로 메소드 이름을 표현하자.
  - should_ReturnTrue_When_DietRecommended()로 바꿔주자.

unit test는 남들이 보기에 한번에 어떤 test인지 파악할 수 있을만큼 가독성이 좋게 만들어야 한다. 다음의 세개의 주석을 달아준다.

- given: input value
- when: 메서드의 결과를 대입하는 구문
- then: assertion - 실행되는지 아닌지

test의 결과가 참이 나오는 것을 해봤으니 거짓이 나오는 test 메소드도 만들어보자.

- 메소드명과 안에 있는 input value 등을 모두 바꿔준다. false가 나오는 값으로 바꿔주자.(assertFalse 포함)

main 폴더에 가면 exception을 던지는 것을 알 수 있다. 이를 위해서도 test를 만들어줘야 한다.

- 이를 위한 test method의 이름은 should_ThrowArithmeticException_When_HeightZero()로 바꿔준다.
- 또한 안에 있는 height부분을 0으로 바꿔준다.(그래야 예외가 발생한다.)
- asset문도 assertThrows로 바꿔준다.
  - 여기에 해당하는 파라미터는 ArithmeticException을 던져줘야 하므로 첫째 칸에는 ArithmeticException을 넣어준다.
  - assertThrows의 두번째 파라미터를 만들어야 한다.
    - 45번째 줄에서 보면 어차피 exception을 날리는 것을 알고 있으므로, 우리는 이 구문을 실행하는 것 대신에 구문 실행을 할 가능성만 생각해서 Executable로 바꿔준다. Ctrl+space로 junit것을 상속한다.

@Test

- 이 어노테이션을 쓰지 않는다면, test는 실행되지 않는다.

***

>ssertTrue  
assertFalse  
assertThrows  
assertEquals  
assertAll  
assertNull  
assertEquals  
assertArrayEquals  
@BeforeEach  
@AfterEach  
@BeforeAll  
@AfterAll  
assertTimeout  
assumeTrue  
@Nested  
@DisplayName  
@Disabled  
@DisabledOnOs  
***

## Parameterized test

@test 자리에 @ParameterizedTest를 적는다.
메서드 안에 value를 적지 않고 어노테이션에 적어서 여러 경우들을 고려해보는 것이다.(@ValueSource(types = {~}): value를 입력하는 어노테이션)

- 어노테이션으로 value를 생성했으므로 메서드의 파라미터에 이를 의미하는 type과 변수 이름을 작성한다. 그럼 이 파라미터로 초기화되는 것이므로, 숫자로 초기화 되어있던 자리에 파라미터 변수 이름을 기입한다.
- 두개의 파라미터를 동시에 parameterized test를 진행하려면 @ValueSource가 아닌 @CsvSource를 써야한다.(Comma Seperated Value Source)
- @CsvSource 형식
  - @CsvSource(value = {"77.0, 99.0"}) 이런식으로 두 value를 ""로 묶어준다.
  - 물론 메소트의 파라미터에도 임의의 변수를 선언해줘야 한다.
- @ParameterizedTest옆에 (name = ",")을 추가해주면, 결과창에서 각 CsvSource에 이름이 붙어 가독성을 높여준다.
- @CsvFileSource: csv파일을 그대로 가져와 value로 쓴다.
  - 파일의 위치를 적어준다. 파일을 보면 첫 줄이 column name인 것을 알 수 있는데 이것은 test 중에 읽으면 안되는 것이므로 numLinesToSkip을 써서 넘겨준다.

***

## Repeated test

@test자리에 @RepeatedTest()를 적어준다. ()안에는 반복 횟수를 적어준다.

- test를 몇번 반복할 것인지 정해주는 어노테이션
- 여기서 또 알 수 있는 것은 AfterEach, BeforeEach는 반복 횟수마다 실행된다.
- @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)을 사용하면, value만큼 반복을 하는데 반복할때마다 메소드의 이름을 붙여서 어떤 메소드를 반복했는지 결과창에서 볼 수 있게 해준다.

***

## Nested

- 메소드 이름별로 코드를 정리해서 나중에 결과창의 가독성을 높여주는 역할을 한다
- @Nested를 붙여서 새로운 class를 생성하는 것이다. 여기서 주의할 점은, 만약 class로 묶었는데 원래 코드에서 this로 가리켰던 outer class는 더이상 this로 참조할 수 없으므로, this 앞에 outer class의 이름을 추가해줘야 한다.

```java
  assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
```

- 여기서 assumeTrue는 아무리 값이 false라도 true로 가정하고 코드를 실행하는 것이다.

***

## Junit 4 vs 5

|   Junit 4    |   Junit 5   |
| :----------: | :---------: |
|   @Before    | @BeforeEach |
|    @After    | @AfterEach  |
| @BeforeClass | @BeforeAll  |
| @AfterClass  |  @AfterAll  |
|   @Ignore    |  @Disabled  |
***

## Public test methods

***Junit 5***<br/>

```java
@Test
void should_ThrowException() //no need for public
```

***Junit 4***<br/>

```java
@Test
public void should_ThrowException() // must be public
```

***

## Testing exceptions

***Junit 5***<br/>

```java
@Test
void should_ThrowException(){
  assertThrow(Exception.class, () -> {});
}
```

***Junit 4***

```java
@Test(expected = Exception.class)
public void should_ThrowException(){
  ...
}
```

***

## Testing performances(timeout)

***Junit 5***

```java
@Test
void should_Timeout(){
  assertTimeout(Duration.ofMillis(1), () -> {...});
}
```

***Junit 4***

```java
@Test(timeout = 1)
public void should_Timeout(){
  ...
}
```

***

## New in Junit 5

- @Nested
- @RepeatedTest
