# 电影院管理系统
  本项目开发了一个影城管理系统。用户包含管理员、经理、前台、顾客共四类。影城有5个放映厅、每个放映厅7排、每排12人，其中人数可通过全局变量修改。
# 一、需求分析
## 1.实体对象：
影城：包括放映厅、座位等。  
放映厅：具有座位数、排数等属性。  
用户：分为管理员、经理、前台和普通用户，拥有各自的权限和功能。  
电影：包括片名、导演、主演、剧情简介、时长等信息。  
场次：包括时间段、价格等信息。  
座位：分为已订和空闲两种状态。  
电影票：包括电子ID编号等信息。  
## 2.参与者及对应的功能：
### 管理员的功能及需求：  
(1) 密码管理  
修改自身密码、重置指定用户/用户的密码（可以重置经理、前台、顾客的密码，但不能查看其他用户的密码）。
(2)用户管理  
(2.1)列出所有用户的用户ID、用户名、用户级别（金牌用户、银牌用户、铜牌用户）、用户注册时间、用户累计消费总金额、用户累计消费次数、用户手机号、用户邮箱；  
(2.2)删除用户信息：  
使用此操作时系统会给出警告提示，确认是否继续删除操作。  
(3.3)查询用户信息  
可以根据用户ID或者用户的用户名进行查询，也可以一次查询所有用户的信息。  
(3)退出登录  
退出登录。  
### 经理的功能及需求:  
(1)影片管理  
列出所有正在上映影片的片名、导演、主演、剧情简介、时长。  
添加、修改、删除影片信息，删除之前给出警告提示：删除后无法恢复，用户确认是否继续删除操作。  
根据影片名称、导演、主演进行单独查询或者组合查询（例如：查询流浪地球、吴京的电影清单）。  
(2)排片管理  
(2.1) 增加场次  
将上映的影片安排在指定的放映厅和指定的时间段、价格，允许提前安排一周的电影场次。不同场次的价格不同。不同电影的定价不同。  
(2.2) 修改场次  
修改场次信息，例如：原来安排放映影片A的放映厅和时段、价格，安排放映电影B，或者空场（不进行安排）。  
(2.3) 删除场次  
删除指定的片场信息，例如：原来放映影片A的放映厅，时段22:00的场次，后续不再安排上映，删除该场次。  
(2.4) 列出所有场次信息  
列出近一周所有场次的信息。  
(3)登录  
(4)退出  
### 前台的功能及需求:
（1）登录  
（2）退出  
（3）列出所有正在上映影片的信息  
影片的信息包括：片名、导演、主演、剧情简介、时长（片长时间）。  
（4）列出所有正在上映影片的信息  
列出所有场次的信息，默认是最近一周。  
列出指定电影和场次的信息  
输入片名和场次，列出该场次座位信息，包括总座位数、空闲座位数和座位信息  
1   2   3   4   5  6  7  8  9  10  11  12  
1  _   _  _   _   _   _  _   _  _   _    _   
2  _   _  _   _   X   X  _   _  _   _    _   
3  _   _  _   _   X   X  _   _  _   _    _   
4  _   _  _   X   X   X  X   _  _   _    _   
5  _   _  _   X   X   X  X   _  _   _    _   
6  _   _  _   _   _   _  _   _  _   _    _   
7  _   _  _   _   _   _  _   _  _   _    _   
其中’_’表示座位为空，’X’表示已经有人选中该座位。  
(5)售票  
输入片名和场次、用户名/手机号、支付金额，输出电影票信息（包括电影票的电子ID编号）。  
### 顾客的功能及需求:
(1)注册：  
用户名长度不少于5个字符；密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。  
(2)登录：密码连续输入错误5次就锁定账户。  
(3)密码管理  
(3.1)修改自身密码  
密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。  
(3.2)忘记密码：可以让自行重置密码  
模拟重置密码功能，当用户选择忘记密码功能的时候，让用户输入用户名和注册所使用的邮箱地址，系统会将一个随机生成的密码发到指定的邮箱。并提示用户可以登录到邮箱查看登录密码。  
(4)购票  
(4.1)查看所有电影放映信息  
执行该功能，显示所有近期上映的电影的信息：片名、导演、主演、剧情简介和片长。  
(4.2)查看指定电影放映信息  
执行该功能，显示指定电影的信息：片名、场次（放映厅和时段）。   
(4.3)购票  
选定场次以后，显示座位信息（“O”表示空闲，“X”表示占用），用键盘输入座位号后锁定座位2分钟，如果2分钟后支付，则该购票生效。2分钟后还没支付，则该座位被释放。注意：一次可以购买多张电影票。  
(4.4)付账（模拟的支付渠道：支付宝、微信、银行卡）  
模拟支付操作，不调用实际的支付宝、微信、银行卡。操作成功以后，生成电影票的电子ID编号，凭此编号可以取票。  
注意：用户级别不同，享受的折扣不一样，金牌用户：88折、银牌用户（95折）、铜牌用户（不打折）。  
(4.5) 取票  
输入电影票的电子ID编号，取票。如果票已被取出，提示用户票已被取，不能重复取票。  
(5)退出登录  
# 版本迭代
## 1.0版
  使用DAO模式，利用内存存储数据，实现管理系统的基本功能。
## 1.1版
  利用文件操作，将对象状态保存到文件中。
## 1.2版
   将用户密码加密后再保存到文件中