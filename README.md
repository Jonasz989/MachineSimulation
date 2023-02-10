# MachineSimulation

For english below

Podczas laboratorium należy zbudować aplikację o przyjaznym, graficznym interfejsie użytkownika. Interfejs ten powstać ma w oparciu o klasy SWING lub klasy JavaFX (opcjonalnie).
Budowana aplikacja służyć ma do wizualizacji działania prostej maszyny jak na załączonym rysunku.
![image](https://user-images.githubusercontent.com/76077510/217991739-1b9436e4-0240-4119-87b3-694dc6f21685.png)

Maszynę umieszczono w układzie współrzędnych x,y. Maszyna składa się z dwóch przegubowo połączonych ramion. Pierwsze jest napędzane i wykonuje ruch posuwisto-zwrotny wzdłuż osi y (na rysunku pokazano położenia krańcowe), zaś ruch drugiego ramienia wynikać ma z nałożonych ograniczeń. To drugie ramię przechodzi przez przegubowo umocowany suwak. Geometria maszyny parametryzowana jest wartościami: l1 – długość pierwszego ramienia, l2 – długość drugiego ramienia, s - maksymalny skok, d i h – odpowiednio, odległość w poziomie i w pionie suwaka od początku układu współrzędnych. Jak widać środek suwaka, i zarazem jego oś obrotu, położone są w punkcie ps=(d,h).
Aplikacja powinna umożliwiać wprowadzenie parametrów maszyny oraz pozwalać na uruchomienie symulacji (polegającej na wykonaniu pełnego ruchu posuwisto-zwrotnego przez ramię l1). Na interfejsie użytkownika powinny pojawić się wykresy składowych prędkości w poziomie i pionie punktu p2 (wykresy składowych vx oraz vy). Prędkości te należy wyliczyć poprzez całkowanie położenia tego punktu. Można to zrobić stosując pewne uproszczenie. Wystarczy, by podczas symulacji w kolejnych krokach wyliczać różnice pomiędzy bieżącym a poprzednim położeniem punktu p2. Prędkość będzie proporcjonalna do tej różnicy. Oczywiście przyrosty kąta α powinny być odpowiednio małe (np. pół stopnia).
Budowana aplikacja ma być modularna. W związku z tym w jej deskryptorze module-info.java powinny pojawić się odpowiednie wpisy dotyczące zależności. W przypadku użycia klas SWING będzie to:
module okienka {
	requires java.desktop;
}
zaś w przypadku użycia klas JavaFX będzie to:
module SimpleFX {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
Ponieważ klasy SWING należą do standardowej dystrybucji JDK komenda uruchamiająca aplikację nie wymaga korygowania ścieżki modułowej. W przypadku umieszczenia skompilowanych klas aplikacji w katalogu "cośtam\Okienka\bin" komenda ta przybierze postać:
> java.exe -p "cośtam\Okienka\bin" -m okienka/app.Ramka
Jeśli zbuduje się jara z aplikacją, to tego jara dokłada się do ścieżki modułowej:
> java.exe -p okienka.jar -m okienka/app.Ramka
JavaFX to osobny runtime wymagający osobnej instalacji. W związku z tym komenda uruchamiająca aplikację musi uwzględniać położenie modułów dostarczanych przez ten runtime. Komenda ta przybiera zwykle postać:
> java -p "cośtam\SimpleFX\bin;E:\Java\javafx-sdk-17.0.2\lib\javafx.base.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.controls.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.fxml.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.graphics.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.media.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.swing.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx.web.jar;E:\Java\javafx-sdk-17.0.2\lib\javafx-swt.jar" -m SimpleFX/application.Main
W przykładzie powyżej uwzględniono ścieżki do wszystkich modułów. Faktycznie wystarczyłoby wskazać ścieżki tylko do modułów wymaganych. Podobnie jak w poprzednim przypadku zamiast ścieżki do skompilowanych klas aplikacji można byłoby wskazać zbudowanego jara.
Pracując z modułami Java nie da się tak swobodnie korzystać z bibliotek skompilowanych klas jak to było wcześniej. Normalnie nie da się zrobić fatjara, zamieszczając w nim w jednym korzeniu kody bajtowe klas pochodzące z różnych bibliotek. Teraz bowiem mamy do czynienia z modułami, z których każdy ma własny deskryptor module-info. A zgodnie ze specyfikacją (https://docs.oracle.com/javase/9/docs/specs/jar/jar.html#Modular) modularny jar może zawierać tylko jeden deskryptor modułu (plik module-info.class) umieszczony w głównym katalogu archiwum. Tak wiec normalnie nie da się w jednym jarze umieścić wielu modułów. Istnieją obejścia tego problemu - poprzez implementację własnego ładowacza klas, który będzie "wyciągał" moduły spakowane w jakąś strukturę wewnątrz jara. Ale to trochę skomplikowana sprawa.
Zwykle jeden jar zawiera jeden moduł. Wybrane moduły można zlinkować tworząc własne środowisko uruchomieniowe. Linkowanie odbywa się za pomocą narzędzia jlink (patrz: https://livebook.manning.com/book/the-java-9-module-system/chapter-4/76 https://www.developer.com/design/how-modules-are-packaged-in-java-9/ ). Można to zrobić tak (z odpowiednim atrybutami do opcji -p, --add-modules)
> jlink -p . --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,myfxdemo --launcher runapp=myfxdemo/org.openjfx.MyApp --output app
W efekcie powstanie katalog, a w nim wszystko co potrzeba do uruchomienia wskazanej klasy (bynajmniej nie jest to jeden plik). Jednak uwaga - w przypadku bibliotek natywnych trzeba jeszcze skopiować do tego środowiska zależności w postaci bibliotek ładowane dynamicznie. Tak więc jeśli buduje się aplikację w całości modularnie, to nie da się zrobić jednego fatjara.
Pozostałe szczegóły mają być zgodne z ustaleniami poczynionymi na początku zajęć.


During the lab, an application with a user-friendly graphical user interface is to be built. This interface is to be based on SWING or JavaFX classes (optional).
The application to be built is to visualise the operation of a simple machine as shown in the attached figure.

The machine is placed in the x,y coordinate system. The machine consists of two articulated arms. The first is driven and carries out reciprocating motion along the y-axis (the end positions are shown in the figure), while the motion of the second arm is to result from imposed constraints. This second arm passes through an articulated slider. The geometry of the machine is parameterised with the following values: l1 - length of the first arm, l2 - length of the second arm, s - maximum stroke, d and h - respectively, the horizontal and vertical distance of the slider from the origin of the coordinate system. As can be seen, the centre of the slider, and at the same time its axis of rotation, is located at the point ps=(d,h).
The application should allow the user to enter machine parameters and run a simulation (involving a full reciprocating motion through the l1 arm). The user interface should display graphs of the horizontal and vertical components of the velocity of the p2 point (graphs of the vx and vy components). These velocities should be calculated by integrating the position of this point. This can be done using a certain simplification. It is sufficient to calculate the difference between the current and previous position of point p2 in successive steps during the simulation. The velocity will be proportional to this difference. Of course, the increments of angle α should be sufficiently small (e.g. half a degree).
The application to be built is intended to be modular. Therefore, appropriate dependency entries should appear in its module-info.java descriptor. If SWING classes are used, this will be:
module windows {
	requires java.desktop;
}
while if JavaFX classes are used, this will be:
module SimpleFX {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
Since the SWING classes belong to the standard JDK distribution the command that launches the application does not need to correct the module path. If you place the compiled classes of the application in the "somethingWindows" directory, the command will take the form:
> java.exe -p "somethingtamWindowsbin" -m windows/app.Framework
If one builds a jar with the application, this jar is added to the module path:
> java.exe -p windows.jar -m windows/app.Frame
JavaFX is a separate runtime that requires a separate installation. Therefore, the command that starts the application must take into account the location of the modules provided by this runtime. This command usually takes the form of:
   2libjavafx.graphics.jar;E:Javajavafx-sdk-17.0.2libjavafx.media.jar;E:Javajavafx-sdk-17.0.2libjavafx. swing.jar;E:Javavafx-sdk-17.0.2libjavafx.web.jar;E:Javavafx-sdk-17.0.2libjavafx-swt.jar" -m SimpleFX/application.Main
In the example above, the paths to all modules are included. In fact, it would be sufficient to indicate the paths only to the required modules. As in the previous case, the built jar could be indicated instead of the path to the compiled application classes.
When working with Java modules, it is not possible to use libraries of compiled classes as freely as before. Normally, it is not possible to make a fatjar by including byte codes of classes from different libraries in one root. This is because now we are dealing with modules, each of which has its own module-info descriptor. And according to the specification (https://docs.oracle.com/javase/9/docs/specs/jar/jar.html#Modular), a modular jar can only contain one module descriptor (the module-info.class file) located in the root of the archive. So normally it is not possible to put multiple modules in one jar. There are workarounds for this problem - by implementing a custom class loader that will 'pull' modules packed into some structure inside the jar. But this is a bit of a tricky business.

Typically, one jar contains one module. Selected modules can be linked to create your own runtime environment. Linking is done using the jlink tool (see: https://livebook.manning.com/book/the-java-9-module-system/chapter-4/76 https://www.developer.com/design/how-modules-are-packaged-in-java-9/ ). It can be done like this (with appropriate attributes to the -p, --add-modules option)
> jlink -p . --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,myfxdemo --launcher runapp=myfxdemo/org.openjfx.MyApp --output app
The result will be a directory with everything you need to run the indicated class (by no means a single file). But beware - in the case of native libraries, you still need to copy the dependencies in the form of dynamically loaded libraries into this environment. So if you are building an application entirely modularly, it is not possible to make one fatjar.
The remaining details are to be as agreed at the beginning of the class.

Translated with www.DeepL.com/Translator (free version)
