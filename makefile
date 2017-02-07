JFLAGS = -g -d bin
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		src/Main.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) bin/*.class