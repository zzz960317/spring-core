package hello.springcore.beanfind;

import hello.springcore.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object + " + bean);
        }
}

@Test
@DisplayName("애플리케이션 빈 출력하기")
void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
        BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

        // Role ROLE_APPLICATION (애플리케이션에 등록된 빈)
        // Role ROLE_INFRASTRUCTURE (스프링 내부에서 사용하는 빈)
        if (beanDefinition.getRole() == beanDefinition.ROLE_INFRASTRUCTURE) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " ,object = " + bean);
        }
    }
}
}

