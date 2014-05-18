/*
CREATE TABLE forum (
  id            SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(250)      NOT NULL,
  owner_id      INT UNSIGNED      NOT NULL,
  date_created  TIMESTAMP DEFAULT 0,
  date_modified TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES account (id),
  UNIQUE INDEX forum_idx1 (name)
)
  ENGINE = InnoDb;

CREATE TABLE message (
  id            INT UNSIGNED      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  forum_id      SMALLINT UNSIGNED NOT NULL,
  subject       VARCHAR(250)      NOT NULL,
  author_id     INT UNSIGNED      NOT NULL,
  text          TEXT(8000)        NOT NULL,
  visible       BOOLEAN           NOT NULL DEFAULT 1,
  date_created  TIMESTAMP DEFAULT 0,
  date_modified TIMESTAMP DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (forum_id) REFERENCES forum (id),
  FOREIGN KEY (author_id) REFERENCES account (id),
  INDEX message_idx1 (date_created)
)
  ENGINE = InnoDb;
*/

DELIMITER //

CREATE PROCEDURE createForum($name VARCHAR(250), $owner_id INT, OUT $id SMALLINT)
  BEGIN
    INSERT INTO forum (name, owner_id) VALUES ($name, $owner_id);
    SET $id := last_insert_id();
  END //

CREATE PROCEDURE createMessage($forum_id INT, $author_id INT, $create_date TIMESTAMP, $subject VARCHAR(250))
  BEGIN
    INSERT INTO message (forum_id, subject, author_id, date_created, text) VALUES
      ($forum_id, $subject, $author_id, $create_date,
       '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum.</p><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum.</p>');
  END //


-- Create forums and messages

call createForum('Algebra I', @julia, @forum);
call createMessage(@forum, @julia, '2012-09-28 12:34:03', 'What *is* a variable?');
call createMessage(@forum, @juan, '2012-09-30 12:34:19', 'This class is too hard');
call createMessage(@forum, @julia, '2012-10-01 14:05:21', 'Curses, Descartes');

call createForum('Algebra II/Trigonometry', @julia, @forum);
call createMessage(@forum, @elvira, '2012-09-29 04:01:39', 'now i know how tall that pyramid is');
call createMessage(@forum, @paula, '2012-09-30 16:04:11', 'When will I ever use this??');
call createMessage(@forum, @elvira, '2012-09-30 14:30:21', 'buy v1@gRA 0nL1n3!');
update message set visible = 0 where id = last_insert_id();
call createMessage(@forum, @daniel, '2012-10-01 19:37:00', 'Solving system of linear equations');
call createMessage(@forum, @daniel, '2012-10-01 21:58:42', 'Need help applying Gaussian elimination');

call createForum('Precalculus', @julia, @forum);
call createMessage(@forum, @julia, '2012-09-27 16:32:09', 'formula for computing the volume of a sphere');
call createMessage(@forum, @juan, '2012-10-01 17:48:02', 'Isn''t a 96-gon basically the same as a circle');
call createMessage(@forum, @julia, '2012-10-01 17:53:36', 'Join my precalc Facebook group');

call createForum('Calculus I', @elvira, @forum);

call createForum('Calculus II', @elvira, @forum);
call createMessage(@forum, @elvira, '2012-09-27 12:34:56', 'Relationship between differentiation and integration');
call createMessage(@forum, @daniel, '2012-09-30 12:43:45', 'Integrating a volume');
call createMessage(@forum, @paula, '2012-10-01 08:23:02', 'epsilon-delta definition of a limit');
call createMessage(@forum, @julia, '2012-10-01 09:56:39', 'Newton or Leibniz');
call createMessage(@forum, @julia, '2012-10-01 11:02:01', 'Help!!! Too many integration rules');

call createForum('Model theory of second-order intuitionistic modal logics', @elvira, @forum);
call createMessage(@forum, @elvira, '2012-09-23 14:29:06', 'Possible worlds semantics');
call createMessage(@forum, @daniel, '2012-09-28 14:31:22', 'Kripke on naming and necessity');
call createMessage(@forum, @julia, '2012-09-30 16:17:16', 'Nonconstructive proof that P != NP. Is it good enough??');
call createMessage(@forum, @paula, '2012-09-30 19:43:53', 'Who is Archimedes Plutonium?');





<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">

    <!--
    <bean id="sessionFactory"
           class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mappingResources">
            <list>
                <value>com/acme/Bank.hbm.xml</value>
                <value>com/acme/Account.hbm.xml</value>
                <value>com/acme/Customer.hbm.xml</value>
            </list>
        </property>
    </bean>

    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="url" value="jdbc:hsqldb:hsql://localhost:9001"/>
        <property name="driverClassName" value="org.hsqldb.jdbcDriver"/>
        <property name="username" value="sa"/>
        <property name="password" value=""/>
</bean>
-->
</beans>


<dependency>
<groupId>org.eclipse.persistence</groupId>
<artifactId>eclipselink</artifactId>
<version>2.5.1</version>
<scope>provided</scope>
</dependency>
<dependency>
<groupId>org.eclipse.persistence</groupId>
<artifactId>org.eclipse.persistence.jpa.modelgen.processor</artifactId>
<version>2.5.1</version>
<scope>provided</scope>
</dependency>




@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
List<GrantedAuthority> authorities = new ArrayList<>();
// fetch user from e.g. DB
if ("client".equals(username)) {
authorities.add(new SimpleGrantedAuthority("CLIENT"));
User user = new User(username, "pass", true, true, false, false, authorities);

return user;
}
if ("admin".equals(username)) {
authorities.add(new SimpleGrantedAuthority("ADMIN"));
User user = new User(username, "pass", true, true, false, false, authorities);

return user;

} else {
return null;
}
}




Offending resource: ServletContext resource [/WEB-INF/applicationContext.xml]; nested exception is org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line 15 in XML document from ServletContext resource [/WEB-INF/security-config.xml] is invalid; nested exception is org.xml.sax.SAXParseException; lineNumber: 15; columnNumber: 54; cvc-complex-type.2.4.a: Invalid content was found starting with element 'http'. One of '{"http://www.springframework.org/schema/security":intercept-url, "http://www.springframework.org/schema/security":access-denied-handler, "http://www.springframework.org/schema/security":form-login, "http://www.springframework.org/schema/security":openid-login, "http://www.springframework.org/schema/security":x509, "http://www.springframework.org/schema/security":jee, "http://www.springframework.org/schema/security":http-basic, "http://www.springframework.org/schema/security":logout, "http://www.springframework.org/schema/security":session-management, "http://www.springframework.org/schema/security":remember-me, "http://www.springframework.org/schema/security":anonymous, "http://www.springframework.org/schema/security":port-mappings, "http://www.springframework.org/schema/security":custom-filter, "http://www.springframework.org/schema/security":request-cache, "http://www.springframework.org/schema/security":expression-handler, "http://www.springframework.org/schema/security":headers, "http://www.springframework.org/schema/security":csrf}' is expected.


   <http security="none" pattern="/login" />
    
    <!-- 
    <http security="none" pattern="/login" />
    <http security="none" pattern="/resources/**" />
    <http security="none" pattern="/favicon.ico" />

    <http auto-config="false"  use-expressions="true">
        <intercept-url pattern="/UIDL/**" access="isAuthenticated()" />
        <intercept-url pattern="/VAADIN/**" access="permitAll" />
        <intercept-url pattern="/login" access="permitAll" />
        <intercept-url pattern="/logout" access="permitAll" />
        <intercept-url pattern="/accessdenied" access="permitAll" />
        <intercept-url pattern="/**" access="hasRole('AUTHORITY_USER')" />
        <form-login login-page="/login" authentication-failure-url="/accessdenied" />
        <logout logout-success-url="/logout" />
    </http>
     -->


    
    <!--
    <beans:bean id="passwordEncoder" class="org.springframework.security.authentication.encoding.ShaPasswordEncoder">
        <beans:constructor-arg value="512" />
        <beans:property name="iterations" value="987" />
    </beans:bean>
    
    <beans:bean id="saltSource" class="org.springframework.security.authentication.dao.ReflectionSaltSource">
        <beans:property name="userPropertyToUse" value="username" />
    </beans:bean>
    -->



        <!-- <authentication-provider>
            <user-service>
                <user name="TEST" password="1234" authorities="AUTHORITY_USER" />
            </user-service>
        </authentication-provider>  -->




    
    <!-- 
            <property name="eventListeners">
            <map>
                <entry key="post-insert" value-ref="envers" />
                <entry key="post-update" value-ref="envers" />
                <entry key="post-delete" value-ref="envers" />
                <entry key="pre-collection-update" value-ref="envers" />
                <entry key="pre-collection-remove" value-ref="envers" />
                <entry key="post-collection-recreate" value-ref="envers" />
            </map>
        </property>
        
   <bean id="envers" class="org.hibernate.envers.event.AuditEventListener" />
    -->

    <!-- DAOs and service beans
    <context:component-scan base-package="com.vaadin.demo.dashboard.dao.hibernate"/>
    <context:component-scan base-package="com.vaadin.demo.dashboard.service"/>
    -->



    <!--
   <util:properties id="eventListeners">
       <prop key="pre-insert">timestampListener</prop>
       <prop key="pre-update">timestampListener</prop>
       <prop key="post-insert">auditListener</prop>
       <prop key="post-update">auditListener</prop>
       <prop key="post-delete">auditListener</prop>
       <prop key="pre-collection-update">auditListener</prop>
       <prop key="pre-collection-remove">auditListener</prop>
       <prop key="post-collection-recreate">auditListener</prop>
   </util:properties>
   -->


    <!--
   <bean id="auditListener" class="org.hibernate.envers.event.AuditEventListener"/>
   <bean id="timestampListener" class="ch.frankel.blog.envers.event.TimestampListener"/>
   -->