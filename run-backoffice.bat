REM set the class path, eg.
SET EAPLI_CP=backoffice.consoleapp\target\ecafeteria.backoffice.consoleapp-1.0-SNAPSHOT.jar;consoleapp.common\target\consoleapp.common-0.0.1-SNAPSHOT.jar;ecafeteria.bootstrapapp\target\ecafeteria.bootstrapapp-0.0.1-SNAPSHOT.jar;ecafeteria.core\target\ecafeteria.core-1.0-SNAPSHOT.jar;framework\target\framework-1.0-SNAPSHOT.jar;util\target\util-1.0-SNAPSHOT.jar; 
REM SET EAPLI_CP=%EAPLI_CP%;LIBS\eclipselink\eclipselink-2.3.2.jar;LIBS\eclipselink\javax.persistence-2.0.3.jar;LIBS\eclipselink\org.eclipse.persistence.jpa.jpql_1.0.1.jar;LIBS\h2-1.3.171.jar;LIBS\jfreechart-1.0.14.jar;LIBS\jcommon-1.0.17.jar

REM call the java VM, e.g, 
java -cp %EAPLI_CP% eapli.ecafeteria.backoffice.consoleapp.ECafeteriaBackoffice
