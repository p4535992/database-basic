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

set CLASSPATH=%APP_HOME%\lib\database-basic-1.6.8.jar;%APP_HOME%\lib\groovy-all-2.3.11.jar;%APP_HOME%\lib\slf4j-api-1.7.14.jar;%APP_HOME%\lib\spring-jdbc-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-core-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-context-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-web-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-orm-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-4.2.4.RELEASE.jar;%APP_HOME%\lib\jooq-3.7.2.jar;%APP_HOME%\lib\jooq-meta-3.7.2.jar;%APP_HOME%\lib\jooq-codegen-3.7.2.jar;%APP_HOME%\lib\tika-app-1.11.jar;%APP_HOME%\lib\tika-core-1.11.jar;%APP_HOME%\lib\tika-parsers-1.11.jar;%APP_HOME%\lib\mysql-connector-java-5.1.38.jar;%APP_HOME%\lib\hsqldb-2.3.3.jar;%APP_HOME%\lib\h2-1.4.191.jar;%APP_HOME%\lib\bonecp-0.8.0.RELEASE.jar;%APP_HOME%\lib\hibernate-core-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-entitymanager-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-osgi-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-envers-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-c3p0-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-proxool-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-infinispan-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-ehcache-5.0.7.Final.jar;%APP_HOME%\lib\hibernate-validator-5.3.0.Alpha1.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\eclipselink-2.5.1.jar;%APP_HOME%\lib\joda-time-2.9.2.jar;%APP_HOME%\lib\usertype.core-5.0.0.GA.jar;%APP_HOME%\lib\univocity-parsers-2.0.0-SNAPSHOT.jar;%APP_HOME%\lib\opencsv-3.7.jar;%APP_HOME%\lib\jackson-databind-2.7.1.jar;%APP_HOME%\lib\javax.servlet-api-4.0.0-b01.jar;%APP_HOME%\lib\spring-beans-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-tx-4.2.4.RELEASE.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\spring-aop-4.2.4.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.2.4.RELEASE.jar;%APP_HOME%\lib\tika-serialization-1.11.jar;%APP_HOME%\lib\tika-xmp-1.11.jar;%APP_HOME%\lib\tika-batch-1.11.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.12.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.12.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.12.jar;%APP_HOME%\lib\log4j-1.2.17.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\vorbis-java-tika-0.6.jar;%APP_HOME%\lib\jackcess-2.1.2.jar;%APP_HOME%\lib\jackcess-encrypt-2.1.1.jar;%APP_HOME%\lib\jmatio-1.0.jar;%APP_HOME%\lib\apache-mime4j-core-0.7.2.jar;%APP_HOME%\lib\apache-mime4j-dom-0.7.2.jar;%APP_HOME%\lib\commons-compress-1.10.jar;%APP_HOME%\lib\xz-1.5.jar;%APP_HOME%\lib\commons-codec-1.9.jar;%APP_HOME%\lib\pdfbox-1.8.10.jar;%APP_HOME%\lib\bcmail-jdk15on-1.52.jar;%APP_HOME%\lib\bcprov-jdk15on-1.52.jar;%APP_HOME%\lib\poi-3.13.jar;%APP_HOME%\lib\poi-scratchpad-3.13.jar;%APP_HOME%\lib\poi-ooxml-3.13.jar;%APP_HOME%\lib\tagsoup-1.2.1.jar;%APP_HOME%\lib\asm-5.0.4.jar;%APP_HOME%\lib\isoparser-1.0.2.jar;%APP_HOME%\lib\metadata-extractor-2.8.0.jar;%APP_HOME%\lib\boilerpipe-1.1.0.jar;%APP_HOME%\lib\rome-1.0.jar;%APP_HOME%\lib\vorbis-java-core-0.6.jar;%APP_HOME%\lib\juniversalchardet-1.0.3.jar;%APP_HOME%\lib\jhighlight-1.0.2.jar;%APP_HOME%\lib\java-libpst-0.8.1.jar;%APP_HOME%\lib\junrar-0.7.jar;%APP_HOME%\lib\cxf-rt-rs-client-3.0.3.jar;%APP_HOME%\lib\opennlp-tools-1.5.3.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\json-20140107.jar;%APP_HOME%\lib\netcdf4-4.5.5.jar;%APP_HOME%\lib\grib-4.5.5.jar;%APP_HOME%\lib\cdm-4.5.5.jar;%APP_HOME%\lib\httpservices-4.5.5.jar;%APP_HOME%\lib\commons-csv-1.0.jar;%APP_HOME%\lib\sis-utility-0.5.jar;%APP_HOME%\lib\sis-netcdf-0.5.jar;%APP_HOME%\lib\sis-metadata-0.5.jar;%APP_HOME%\lib\geoapi-3.0.0.jar;%APP_HOME%\lib\jboss-logging-3.3.0.Final.jar;%APP_HOME%\lib\javassist-3.18.1-GA.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\geronimo-jta_1.1_spec-1.1.1.jar;%APP_HOME%\lib\jandex-2.0.0.Final.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\org.osgi.core-4.3.1.jar;%APP_HOME%\lib\org.osgi.compendium-4.3.1.jar;%APP_HOME%\lib\c3p0-0.9.2.1.jar;%APP_HOME%\lib\proxool-0.8.3.jar;%APP_HOME%\lib\infinispan-core-7.2.1.Final.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\classmate-1.1.0.jar;%APP_HOME%\lib\commonj.sdo-2.1.1.jar;%APP_HOME%\lib\usertype.spi-5.0.0.GA.jar;%APP_HOME%\lib\commons-lang3-3.3.2.jar;%APP_HOME%\lib\jackson-annotations-2.7.0.jar;%APP_HOME%\lib\jackson-core-2.7.1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\gson-2.2.4.jar;%APP_HOME%\lib\xmpcore-5.1.2.jar;%APP_HOME%\lib\commons-cli-1.2.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\fontbox-1.8.10.jar;%APP_HOME%\lib\jempbox-1.8.10.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.52.jar;%APP_HOME%\lib\poi-ooxml-schemas-3.13.jar;%APP_HOME%\lib\aspectjrt-1.8.0.jar;%APP_HOME%\lib\jdom-1.0.jar;%APP_HOME%\lib\commons-logging-api-1.1.jar;%APP_HOME%\lib\commons-vfs2-2.0.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.0.3.jar;%APP_HOME%\lib\cxf-core-3.0.3.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxrs-3.0.3.jar;%APP_HOME%\lib\opennlp-maxent-3.0.3.jar;%APP_HOME%\lib\jwnl-1.3.3.jar;%APP_HOME%\lib\jcip-annotations-1.0.jar;%APP_HOME%\lib\jna-4.1.0.jar;%APP_HOME%\lib\protobuf-java-2.5.0.jar;%APP_HOME%\lib\jdom2-2.0.4.jar;%APP_HOME%\lib\jsoup-1.7.2.jar;%APP_HOME%\lib\jj2000-5.2.jar;%APP_HOME%\lib\bzip2-0.9.1.jar;%APP_HOME%\lib\udunits-4.5.5.jar;%APP_HOME%\lib\httpcore-4.2.5.jar;%APP_HOME%\lib\quartz-2.2.0.jar;%APP_HOME%\lib\jcommander-1.35.jar;%APP_HOME%\lib\httpclient-4.2.6.jar;%APP_HOME%\lib\httpmime-4.2.6.jar;%APP_HOME%\lib\sis-storage-0.5.jar;%APP_HOME%\lib\sis-referencing-0.5.jar;%APP_HOME%\lib\jsr-275-0.9.3.jar;%APP_HOME%\lib\xml-apis-1.0.b2.jar;%APP_HOME%\lib\mchange-commons-java-0.2.3.4.jar;%APP_HOME%\lib\infinispan-commons-7.2.1.Final.jar;%APP_HOME%\lib\jgroups-3.6.2.Final.jar;%APP_HOME%\lib\jboss-transaction-api_1.1_spec-1.0.1.Final.jar;%APP_HOME%\lib\jboss-marshalling-osgi-1.4.10.Final.jar;%APP_HOME%\lib\xmlbeans-2.6.0.jar;%APP_HOME%\lib\maven-scm-api-1.4.jar;%APP_HOME%\lib\maven-scm-provider-svnexe-1.4.jar;%APP_HOME%\lib\woodstox-core-asl-4.4.1.jar;%APP_HOME%\lib\xmlschema-core-2.1.0.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\c3p0-0.9.1.1.jar;%APP_HOME%\lib\plexus-utils-1.5.6.jar;%APP_HOME%\lib\maven-scm-provider-svn-commons-1.4.jar;%APP_HOME%\lib\regexp-1.3.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\javax.persistence-2.1.0.jar;%APP_HOME%\lib\ehcache-core-2.6.2.jar;%APP_HOME%\lib\guava-17.0.jar

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
