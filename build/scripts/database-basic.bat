@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  database-basic startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and DATABASE_BASIC_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\database-basic-1.6.8.jar;%APP_HOME%\lib\groovy-all-2.3.11.jar;%APP_HOME%\lib\slf4j-api-1.7.14.jar;%APP_HOME%\lib\spring-jdbc-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-core-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-context-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-web-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-orm-4.2.4.RELEASE.jar;%APP_HOME%\lib\jooq-3.7.2.jar;%APP_HOME%\lib\jooq-meta-3.7.2.jar;%APP_HOME%\lib\jooq-codegen-3.7.2.jar;%APP_HOME%\lib\utility-1.6.8-SNAPSHOT.jar;%APP_HOME%\lib\mysql-connector-java-5.1.38.jar;%APP_HOME%\lib\bonecp-0.8.0.RELEASE.jar;%APP_HOME%\lib\univocity-parsers--SNAPSHOT.jar;%APP_HOME%\lib\hibernate-core-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-entitymanager-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-osgi-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-envers-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-c3p0-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-proxool-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-infinispan-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-ehcache-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-validator-5.3.0.Alpha1.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\eclipselink-2.5.1.jar;%APP_HOME%\lib\joda-time-2.9.2.jar;%APP_HOME%\lib\usertype.core-5.0.0.GA.jar;%APP_HOME%\lib\spring-beans-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-tx-4.2.4.RELEASE.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\spring-aop-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.2.4.RELEASE.jar;%APP_HOME%\lib\json-20140107.jar;%APP_HOME%\lib\jackson-databind-2.5.3.jar;%APP_HOME%\lib\jackson-annotations-2.5.3.jar;%APP_HOME%\lib\jackson-core-2.5.3.jar;%APP_HOME%\lib\gson-2.3.1.jar;%APP_HOME%\lib\jsoup-1.8.2.jar;%APP_HOME%\lib\apache-jena-libs-3.0.1.pom;%APP_HOME%\lib\jena-spatial-3.0.1.jar;%APP_HOME%\lib\jena-sdb-3.0.1.jar;%APP_HOME%\lib\sesame-model-2.8.8.jar;%APP_HOME%\lib\sesame-sail-memory-2.8.8.jar;%APP_HOME%\lib\sesame-repository-sail-2.8.8.jar;%APP_HOME%\lib\sesame-queryresultio-text-2.8.8.jar;%APP_HOME%\lib\sesame-sail-nativerdf-2.8.8.jar;%APP_HOME%\lib\sesame-repository-http-2.8.8.jar;%APP_HOME%\lib\sesame-queryresultio-sparqljson-2.8.8.jar;%APP_HOME%\lib\sesame-repository-manager-2.8.8.jar;%APP_HOME%\lib\sesame-rio-api-2.8.8.jar;%APP_HOME%\lib\sesame-repository-api-2.8.8.jar;%APP_HOME%\lib\sesame-sail-api-2.8.8.jar;%APP_HOME%\lib\sesame-query-2.8.8.jar;%APP_HOME%\lib\driver-2.7.3-build1.12.jar;%APP_HOME%\lib\httpclient-4.5.1.jar;%APP_HOME%\lib\poi-3.10-FINAL.jar;%APP_HOME%\lib\poi-ooxml-3.10-FINAL.jar;%APP_HOME%\lib\sevenzipjbinding-9.20-2.00beta.jar;%APP_HOME%\lib\sevenzipjbinding-all-platforms-9.20-2.00beta.jar;%APP_HOME%\lib\javax.interceptor-api-1.2.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.3.0.RELEASE.jar;%APP_HOME%\lib\jansi-1.11.jar;%APP_HOME%\lib\sysout-over-slf4j-1.0.2.jar;%APP_HOME%\lib\log4j-core-2.5.jar;%APP_HOME%\lib\univocity-parsers-2.0.0-SNAPSHOT.jar;%APP_HOME%\lib\jboss-logging-3.3.0.Final.jar;%APP_HOME%\lib\javassist-3.18.1-GA.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\geronimo-jta_1.1_spec-1.1.1.jar;%APP_HOME%\lib\jandex-2.0.0.Final.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\org.osgi.core-4.3.1.jar;%APP_HOME%\lib\org.osgi.compendium-4.3.1.jar;%APP_HOME%\lib\c3p0-0.9.2.1.jar;%APP_HOME%\lib\proxool-0.8.3.jar;%APP_HOME%\lib\infinispan-core-7.2.1.Final.jar;%APP_HOME%\lib\ehcache-core-2.4.3.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\classmate-1.1.0.jar;%APP_HOME%\lib\commonj.sdo-2.1.1.jar;%APP_HOME%\lib\usertype.spi-5.0.0.GA.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\jena-tdb-3.0.1.jar;%APP_HOME%\lib\lucene-core-4.9.1.jar;%APP_HOME%\lib\lucene-spatial-4.9.1.jar;%APP_HOME%\lib\lucene-analyzers-common-4.9.1.jar;%APP_HOME%\lib\lucene-queryparser-4.9.1.jar;%APP_HOME%\lib\spatial4j-0.4.1.jar;%APP_HOME%\lib\solr-solrj-4.9.1.jar;%APP_HOME%\lib\jena-arq-3.0.1.jar;%APP_HOME%\lib\sesame-util-2.8.8.jar;%APP_HOME%\lib\sesame-sail-base-2.8.8.jar;%APP_HOME%\lib\sesame-sail-inferencer-2.8.8.jar;%APP_HOME%\lib\sesame-queryalgebra-evaluation-2.8.8.jar;%APP_HOME%\lib\sesame-queryalgebra-model-2.8.8.jar;%APP_HOME%\lib\sesame-http-client-2.8.8.jar;%APP_HOME%\lib\sesame-queryparser-api-2.8.8.jar;%APP_HOME%\lib\sesame-rio-trig-2.8.8.jar;%APP_HOME%\lib\sesame-rio-turtle-2.8.8.jar;%APP_HOME%\lib\sesame-queryresultio-api-2.8.8.jar;%APP_HOME%\lib\opencsv-3.2.jar;%APP_HOME%\lib\sesame-http-protocol-2.8.8.jar;%APP_HOME%\lib\sesame-rio-binary-2.8.8.jar;%APP_HOME%\lib\sesame-repository-event-2.8.8.jar;%APP_HOME%\lib\httpcore-4.4.3.jar;%APP_HOME%\lib\poi-ooxml-schemas-3.10-FINAL.jar;%APP_HOME%\lib\logback-classic-1.1.3.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.13.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.13.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.13.jar;%APP_HOME%\lib\log4j-api-2.5.jar;%APP_HOME%\lib\mchange-commons-java-0.2.3.4.jar;%APP_HOME%\lib\infinispan-commons-7.2.1.Final.jar;%APP_HOME%\lib\jgroups-3.6.2.Final.jar;%APP_HOME%\lib\jboss-transaction-api_1.1_spec-1.0.1.Final.jar;%APP_HOME%\lib\jboss-marshalling-osgi-1.4.10.Final.jar;%APP_HOME%\lib\lucene-queries-4.9.1.jar;%APP_HOME%\lib\lucene-sandbox-4.9.1.jar;%APP_HOME%\lib\httpmime-4.3.1.jar;%APP_HOME%\lib\zookeeper-3.4.6.jar;%APP_HOME%\lib\wstx-asl-3.2.7.jar;%APP_HOME%\lib\noggit-0.5.jar;%APP_HOME%\lib\jena-core-3.0.1.jar;%APP_HOME%\lib\jena-shaded-guava-3.0.1.jar;%APP_HOME%\lib\jsonld-java-0.7.0.jar;%APP_HOME%\lib\httpclient-cache-4.2.6.jar;%APP_HOME%\lib\libthrift-0.9.2.jar;%APP_HOME%\lib\commons-csv-1.0.jar;%APP_HOME%\lib\commons-lang3-3.3.2.jar;%APP_HOME%\lib\sesame-sail-model-2.8.8.jar;%APP_HOME%\lib\sesame-repository-sparql-2.8.8.jar;%APP_HOME%\lib\mapdb-1.0.7.jar;%APP_HOME%\lib\sesame-queryparser-serql-2.8.8.jar;%APP_HOME%\lib\sesame-rio-datatypes-2.8.8.jar;%APP_HOME%\lib\sesame-rio-languages-2.8.8.jar;%APP_HOME%\lib\sesame-rio-ntriples-2.8.8.jar;%APP_HOME%\lib\xmlbeans-2.3.0.jar;%APP_HOME%\lib\logback-core-1.1.3.jar;%APP_HOME%\lib\jena-iri-3.0.1.jar;%APP_HOME%\lib\xercesImpl-2.11.0.jar;%APP_HOME%\lib\commons-cli-1.3.jar;%APP_HOME%\lib\jena-base-3.0.1.jar;%APP_HOME%\lib\sesame-queryparser-sparql-2.8.8.jar;%APP_HOME%\lib\sesame-queryresultio-sparqlxml-2.8.8.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\dexx-collections-0.2.jar;%APP_HOME%\lib\javax.persistence-2.1.0.jar;%APP_HOME%\lib\guava-18.0.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\xml-apis-1.4.01.jar

@rem Execute database-basic
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %DATABASE_BASIC_OPTS%  -classpath "%CLASSPATH%" com.github.p4535992.database.home.TestMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable DATABASE_BASIC_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%DATABASE_BASIC_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
