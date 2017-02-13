JFLAGS = -g -d bin
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		src/Main.java \
		src/ALU.java \
		src/Instructions.java \
		src/Memory.java \
		src/Registers.java \
		src/Import.java \
		src/Debugger.java \
		src/DebuggerGUI.java \
		src/Decode.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) bin/*.class