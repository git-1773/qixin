
|接口名称|请求头|请求方式|URL地址|
|:--|:--:|:--:|:--|
|登录|无|POST|/auth/login|POST|
* 请求报文:
```java

```
* 响应报文:
```java

```
-----
|接口名称|请求头|请求方式|URL地址|
|:--|:--:|:--:|:--|
|退出登录|Authorization|POST|/auth/logout|POST|
* 请求报文:
```java

```
* 响应报文:
```java

```
-----
|接口名称|请求头|请求方式|URL地址|
|:--|:--:|:--:|:--|
|获取登录用户信息|Authorization|POST|/user/info|POST|
* 请求报文:
```java

```
* 响应报文:
```java

```
-----
|接口名称|请求头|请求方式|URL地址|
|:--|:--:|:--:|:--|
|企业信息申报|Authorization|POST|/company/declare|POST|
* 请求报文:
```java
{
    name, // string 企业名称
    actualPaidCapital, //  integer 实缴资本
    approvalDate,	// date 核准日期
    businessRegistrationNum,	// string 工商注册号
    businessTerm,	//date    营业期限
    capitalUnit,	//Integer    资本金单位
    companyIndustry,	//Integer    公司行业
    companyType,	//Integer    公司类型
    englishName,    //string    英文名
    historyName,	//string    曾用名
    insuredNum,	    //Integer    参保人数
    operaStatus,	//Integer    经营状态
    organizationCode,	//string    组织机构代码
    registeredCapital,	//Integer    注册资本
    registrationAuthority,	//string    登记机关
    setUpDate,	//date    成立日期
    staffSize,	//Integer    人员规模
    taxpayerIdentificationNum,	//string    纳税人识别号
    taxpayerQualification,	//Integer    纳税人资质
    unifiedSocialCreditCode	//string    统一社会信用代码
}
```
* 响应报文:
```java

```
-----