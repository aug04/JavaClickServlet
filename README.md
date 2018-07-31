# JavaClickServlet

Cài đặt môi trường cần thiết và deploy

## Windows
Cài đặt git cho windows => truy cập đường dẫn sau: https://gitforwindows.org/ tải và cài đặt.
Cài đặt TortoiseGit: https://tortoisegit.org/download/ tải về và cài đặt. Sau khi cài đặt xong cần cấu hình đường dẫn tới Git, thực hiện bước sau: Click chuột phải ở màn hình Desktop hoặc 1 thư mục nào đó => Chọn TortoiseGit => Chọn General => trỏ đường dẫn Git.exe path tới thư mục "bin" của Git vừa cài ở bước trên (ví dụ: C:\Program Files\Git\bin).

Kéo source về máy tính, thực hiện: Tạo 1 thư mục trên Desktop chẳng hạn, tên "GitHub" tất nhiên tên tùy ý đặt, sau đó truy cập => Copy link sau "https://github.com/aug04/JavaClickServlet.git" => Click chuột phải chọn "Git clone..." => Dán link vừa copy vào phần "URL:" và bấm nút "OK" => chờ TortoiseGit kéo source về. Sau khi kéo source về ta có thư mục "JavaClickServlet".

Download và cài đặt các phần mềm sau: jdk7, mysql server 5. Sau khi cài đặt xong thì tiến hành cấu hình JAVA_HOME có thể đặt thêm MYSQL_HOME để tiện sử dụng (cách cài đặt và cấu hình tìm kiếm trên Google), có thể sử dụng Navicat để thao tác với MySQL.

Sau khi cài đặt xong thiết đặt thêm JAVA_OPTS, thực hiện như sau: Chuột phải vào Computer > Properties > Advanced system settings > Tab Advanced > Enviroment Variables... > System variables > New... và thực hiện nhập: Variable name: "JAVA_OPTS" (không chứa dấu ngoặc kép), Variable value: "-Xms128m -Xmx1024m -XX:MaxPermSize=256m" (không chứa dấu ngoặc kép) sau đó lưu lại.

Import database vào MySQL, trong thư mục JavaClickServlet\Database\click_servlet.sql thực hiện import file này vào 1 DB tên click_servlet (cách import tìm kiếm trên Google nếu không rõ).

Cấu hình WebServer để chạy ứng dụng web: truy cập JavaClickServlet\WebServer sẽ có file chứa link hướng dẫn cấu hình tomcat, xem và làm theo hoặc có thể sử dụng luôn thư mục "apache-tomcat-7.0.90" đã được cấu hình sẵn. Xem cách cấu hình CATALINA_HOME trên Google

Cấu hình Maven để build ứng dụng web: JavaClickServlet\apache-maven-2.2.1-bin.zip giải nén file này ví dụ đường dẫn: C:\Program Files\Apache\maven sau đó tìm cách cấu hình MAVEN_HOME trên Google, cũng sẽ tương tự như cách cấu hình JAVA_HOME hay JAVA_OPTS

Cấu hình 1 vài thuộc tính về kết nối tới database và đường dẫn lưu file log: JavaClickServlet\SourceCode\ClickServlet\src\main\resources\config.properties thiết đặt các thông số trong file này cho chuẩn với môi trường của bạn

Tiến hành build project bằng Maven thực hiện: truy cập JavaClickServlet\SourceCode\ClickServlet, ở đây bạn sẽ thấy có file pom.xml, mở Command Prompt tại thư mục này và chạy lệnh sau:
```
mvn clean compile install
```
Maven sẽ tiến hành download thư viện và build, sau khi BUILD SUCCESSFUL sẽ có thư mục tên "target" copy file "ad.war" trong thư mục này và paste vào thư mục "webapps" trong ${CATALINA_HOME}\webapps và sau đó vào thư mục ${CATALINA_HOME}\bin chạy file "startup.bat"

Sau khi chạy xong, vào trình duyệt theo liên kết http://localhost:8080/ad ở đây sẽ có giao diện click cũng như quản lý ad và media.
Lưu ý: để xem log, truy cập đường dẫn trong file config.properties nếu cấu hình ở phía trên đã nêu, nếu không cấu hình mặc định logs được ghi tại ${CATALINA_HOME}\logs\ad\

## Linux
Để cài đặt cho linux cũng thực hiện tương tự các bước như ở windows, tuy cách cài đặt có khác nhau giữa 2 hệ điều hành.