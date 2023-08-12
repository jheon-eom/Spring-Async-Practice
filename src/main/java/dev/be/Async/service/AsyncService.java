package dev.be.Async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AsyncService {
    private final EmailService emailService;

    /**
     * 스프링의 비동기 처리를 하기 위해선 스프링 프레임워크의 도움을 받아야 함
     * 1번 메서드의 경우엔 스프링이 프록시로 등록된 빈을 주입받아서 사용
     *
     * 2, 3번 메서드의 경우 스프링에서 프록시로 등록한 객체가 아닌 직접 사용하기 때문에
     * 비동기 처리를 할 수 없음
     */

    // 비동기 올바르게 동작
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // 비동기 동작 안 함
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // 비동기 동작 안 함
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
    }
}
