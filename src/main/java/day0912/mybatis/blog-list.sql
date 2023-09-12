drop table if exists `blog_list`;

create table blog_list
(
    id                 int auto_increment comment '文章编号'
        primary key,
    user_id            int           not null comment '作者的id',
    title              varchar(255)  not null comment '文章标题',
    cover              varchar(255)  null comment '显示文章时的封面',
    `publish_time`     datetime      not null comment '文章发表时间',
    `last_update_time` datetime      not null comment '文章最近更新时间',
    content            text          not null comment '文章内容',
    view               int default 0 null comment '浏览量',
    description        varchar(512)  null comment '简短的描述文章的片段',
    status             int default 0 null comment '文章状态',
    constraint `blog_list_pk`
        unique (id)
);

INSERT INTO `blog_list` (user_id, title, publish_time, last_update_time, content, description, status)
VALUES (1, 'jsp结合了html与java', '2023-09-11 11:28:47', '2023-09-11 11:28:53',
'##### 1.jsp的两种输出方式：<% %>标签 与<%= %>标签
![img_1.png](img/img_1.png)
##### 2.jsp文件转化成java1与class文件可以在以下路径找到
![img_2.png](img/img_2.png)
到达指定路径后继续往下 例如：
\\work\\Catalina\\localhost\\zszdmzmm_web2_war\\org\\apache\\jsp\\jsp
##### 3.jsp本质上是servlet,其转化方式如下。
![img.png](img/img.png)
##### 4.<%! %>标签：将state和behavior写在_jspService()方法之外
![img_3.png](img/img_3.png)
可以看到：红框中的代码都在_jspService()方法之外。
##### 5.通过循环在页面上添加元素
切记java与html的代码各司其职：
![img_4.png](img/img_4.png)', 'jsp', 1);

INSERT INTO `blog_list` (user_id, title, publish_time, last_update_time, content, description, status)
VALUES (1, 'java的基本数据类型(Primitive Data Types)', '2023-09-11 11:28:47', '2023-09-11 11:28:53',
        '### 1.什么是基本数据类型

Java 编程语言是静态类型语言(statically-typed)，这意味着变量的类型需要被明确定义。
例如：

```
    int num = 4;
```

在这个例子中，int是变量的数据类型， num是变量的名称。
我们先将基本数据类型与非基本数据类型的差异按下不表，java支持的基本数据类型共有8种， 这8种又分为4大类：

+ 整数类型：顾名思义，用来存储整数的数据类型。
    - byte: byte 数据类型为8位(1字节)有符号二进制的补码整数，范围在[-128, 128)。
        *     byte num = 127;
    - short: short 数据类型为16位(2字节)有符号二进制的补码整数， 范围在[-32768, 32768)。
        *     short num = 32767;
    - int: int 数据类型为32位(4字节)有符号二进制的补码整数， 范围在[-2^31, 2^31)
      ，在代码中，如果遇到未经定义数据类型的整数常量，默认视为int类型，下列代码中的4和5是int类型，在文章的后面，还会详细提到这点。
        *     int num = 4 + 5;
    - long: long 数据类型为64位(8字节)有符号二进制的补码整数， 范围在[-2^63, 2^63)。该类型在代码的书写与上文有些许差异，如下列例子：
        *     long num = 5L;
+ 浮点数类型：用来储存小数的数据类型。该类型不可用于计算精确值，如货币等。如有需求，需要改用java.math.BigDecimal 类。
    - float: float 数据类型为单精度32位浮点数，与long类型类似，若要让数字表示为float,需要在数字后方加上F标识:
        *     float num = 5.5F;
    - double: double 数据类型为单精度64位浮点数, 如int类型，未经定义的小数常量被视为double类型。
        *     double num = 5.5;
+ 字符类型：
    - char: 用于储存单个字符，如英文字母，汉字，符号等，本质上是一个16位的unicode，例如：
        *     char letter = ''a'';
              char word = ''好'';
              char i = ''\n'';
              char code = ''\u0000'';
+ 布尔类型：
    - boolean: boolean 数据类型只有两个可能的值： true 和 false 。将此数据类型用于跟踪真/假条件的简单标志, 例：
        *     boolean isSunComeFromWest = false;
              boolean isDiceMightBeTheOne = true;

总结完了8种基本数据类型，可以看出，常用的String数据类型并非基本数据类型。

### 2.基本数据类型变量的定义

在上文中，我们已经展示多个例子，但关于定义基本数据类型变量，仍有很多细节。
不妨来看下面的简单例子：

```
    byte num = -3;
```

在这个例子中， 为了更方便理解并了解本质，我们将左侧的num看作一个byte类型容器，而右边，如上文所述，是一个int类型的常量。
这个java语句简单来说实际上是在损失一定精度的情况下，将int类型的数据塞进了byte容器里。

```
-3(int): 原码：1000 0000 ... 0000 0011
         反码：1111 1111 ... 1111 1100
         补码：1111 1111 ... 1111 1101
-3(byte): 只存储后八位： 补码：1111 1101 其中第一位是正负位
                       反码：1000 0010
                       原码：1000 0011 依旧是-3；
```

所幸的是，在这个例子中，这种精度缺失并未造成实际的影响，我们再看下一个例子：

```
    long num=6888888888;  //错误
```

在这个例子中，右边的数据类型仍旧默认是int类型，但其实际超过了int范围，因此编译器报错，正确的写法是：

```
    long num=6888888888L;
```

这样，右边的常量就能被当作long类型处理了。类似的例子还有：

```
    float num=3.5;  //错误
    float num=3.5F; //正确
```

### 3.不同基本数据类型之间的运算

基本数据类型之间的计算有两条规则：

1. 较小的数据类型之间运算可以被较大的数据类型接收，反之则不行。从小到大的顺序是：
   byte, char, short -> int -> long -> float -> double
2. byte, char, short 互相运算，默认转化为int

   以下为一段具体例子：

```
    char ch1 = ''a'';
    byte by1 = 4;
    short sh1 = 5, sh2 = 6;
    int in1 = 6, in2 = 98;
    long lo1 = 6L;

    float fl1 = 3.5F;
    double do1 = 4.5;
    int in3 = by1 + sh1 + ch1;
    int in4 = in1 + in2;
    //char ch2 = sh1 +sh2; 违反2
    // short sh3 = by1 + ch1; 违反2
    // float fl2 = in1 + in2;
    double do2 = fl1 + in1;
    //int in4 = fl1  + do1; 违反1
```

### 4.基本数据类型间的强制转换

前文说过，不同基本数据类型之间的运算存在一定规则，
但在明确知道代码目的的情况下，可以通过强转完成本不能完成的数据类型转化。
例如：
```
    double dou1 = 19.9;
    //取整损耗
    int int1 = (int)dou1; //19

    long long1 = 128L;
    //无损
    short sh1 = (short)long1; //128
    //溢出
    byte by1 = (byte)long1; //-128
```
关于最后一个溢出的例子，同样需要通过详细的存储原理解释。
```
    128(long): 原码：0000 0000 ... 1000 0000
               反码：0000 0000 ... 1000 0000
               补码：0000 0000 ... 1000 0000

    强转成byte时，只留下了后8位:

    128(byte): 补码：1000 0000

    该补码较为特殊， 不可简单的通过取反加一求原码，其指代的数字为-128;
```', '基本数据类型', 1);

INSERT INTO `blog_list` (user_id, title, publish_time, last_update_time, content, description, status)
VALUES (1, 'java基本数据类型与引用数据类型的存储方式', '2023-09-11 11:28:47', '2023-09-11 11:28:53',
        '在虚拟机中，存储位置分为栈(Stack)与堆(Heap)两个位置
基本数据存储的是值，存在栈中，引用数据类型在栈中存储的是地址，地址指向了堆中的一个位置，在堆中相对应的位置放具体的值。
以下是两个例子

1.执行以下代码时：

![img_1.png](img_1.png)

存储过程是：

![img.png](img.png)

2.执行以下代码时
![img_3.png](img_3.png)

存储过程是：

![img_2.png](img_2.png)', '基本数据类型存储', 1);