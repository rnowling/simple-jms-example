Simple Java Messaging Service Example
=====================================

I've found JMS to be a bit complicated to get started with.  Even good books such as "Java Messaging Service" from O'Reilly don't provide a complete project example.  Here I provide the chat example from Chapter 2 with a full build system and instructions.

Building
--------
To build the application:

    $ gradle build

I needed to add the [Apache ActiveMQ](http://activemq.apache.org/) dependency to the Gradle build file using [these maven details](http://activemq.apache.org/activemq-5120-release.html).

Running
-------
You'll need to download the [latest release](http://activemq.apache.org/activemq-5120-release.html) of ActiveMQ.  Untar the file:

    $ tar -xzvf apache-activemq-5.12.0-bin.tar.gz

Then run one of the wrappers in local mode:

    $ ./bin/macosx/activemq console

Or for Linux:

    $ ./bin/linux-x86-64/activemq console

After starting ActiveMQ, you can run the chat programs in other terminals.  Each terminal should be set to the JMS example directory.  To run the example, in each terminal type:

    $ java -cp build/libs/simple-jms-example-0.1.jar:: com.github.rnowling.simplejms.CLIDriver TopicCF topic1 NAME

where `NAME` is the user's name.

Then simply type something and press enter.  You should see output like so:

    this is a test
    Received: RJ: this is a test
    exit

Type `exit` when you want to quit.  ActiveMQ can be exited by pressing Ctrl-C.
