package com.tpe;

//Java tabanlı web uygulamaları web.xml ile config edilir.
//bu classı web.xml yerine kullanacağız.

//AbstractAnnotationConfig.. classı Dispatcher Servletın konfigurasyonu için gerekli adımları gösterir.
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
     dispatcher:
          Servlet WebAppContext:-->view resolver,handler mapping
          Root WebAppContext   :dataya erişim
     */


    @Override
    protected Class<?>[] getRootConfigClasses() {//dataya erişim:JDBC,hibernate konfigürasyonu
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//view resolver,handler mapping konfigurasyonu
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override//hangi url ile gelen istekler servlet tarafından karşılanacak ayarlaması
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }


}