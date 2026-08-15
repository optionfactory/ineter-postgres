# ineter-postgres

**ineter-postgres** is a lightweight Java library that provides custom Hibernate types for PostgreSQL network data types. 
It bridges the gap between [Maltalex's ineter](https://github.com/maltalex/ineter) library and
 the PostgreSQL JDBC driver, allowing you to seamlessly persist IPv4 addresses and subnets in your database.

## Features

* **Native PostgreSQL Types**: Maps `IPv4Address` to the `inet` column type and `IPv4Subnet` to the `cidr` column type.
* **Automatic Registration**: Includes a `NetworkTypesContributor` (`org.hibernate.boot.model.TypeContributor`) that automatically registers the custom JDBC and Java types with Hibernate.
* **Binary Transfer Support**: Includes configuration utilities to enable binary transfer for `inet` and `cidr` objects to optimize performance.

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>net.optionfactory</groupId>
    <artifactId>ineter-postgres</artifactId>
</dependency>