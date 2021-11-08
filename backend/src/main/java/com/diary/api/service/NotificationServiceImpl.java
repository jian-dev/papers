package com.diary.api.service;

import com.diary.api.db.entity.Notification;
import com.diary.api.db.entity.User;
import com.diary.api.response.AlarmDataSet;
import com.diary.api.response.StreamDataSet;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.reflections.Reflections.log;

@Service
public class NotificationServiceImpl implements NotificationService{
    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();
    private static final Map<String, String> CONNECTED_USERS = new ConcurrentHashMap<>();

    @Override
    public void addEmitter(String uuid, AlarmDataSet alarmDataSet) {
//        if (CONNECTED_USERS.containsKey(alarmDataSet.getUserId())) { // 이미 기존에 아이디로 알림 채널이 연결되어 있으면
//            String prevUuid = CONNECTED_USERS.get(alarmDataSet.getUserId());
//            removeEmitter(prevUuid); // 이전 연결을 제거해준다.
//        }
        CLIENTS.put(uuid, alarmDataSet.getEmitter());
        CONNECTED_USERS.put(alarmDataSet.getUserId(), uuid);
        log.info("구독자 추가할 때 map size : " + CLIENTS.size());
    }

    @Override
    public void removeEmitter(String uuid) {
        CLIENTS.remove(uuid);
    }

    @Override
    public void removeUser(String userId) {
        CONNECTED_USERS.remove(userId);
    }

    @Override
    public void publish(String message) {
//        log.info("알림 내용 : " + message);
//        Set<String> deadIds = new HashSet<>();
//        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());
//        CLIENTS.forEach((id, emitter) -> {
//            try {
//                emitter.send(message, MediaType.APPLICATION_JSON);
//                log.info("알림 클라이언트로 보냄");
//            } catch (Exception e) {
//                deadIds.add(id);
//                log.error("* 에러가 발생해서 삭제 목록에 추가함 *");
//            }
//        });
//
//        deadIds.forEach(CLIENTS::remove);
    }

    @Override
    public void publishToUsers(int type, User user, List<String> userIdList) {
        String messageType = "";
        switch (type) {
            case 1: messageType = "일기장 초대"; break;
            case 2: messageType = "일기장 작성"; break;
            case 3: messageType = "일기 감정 표현"; break;
            default: break;
        }

        log.info("알림 내용 : " + messageType);
        Set<String> deadUuids = new HashSet<>();
        Set<String> deadUserIds = new HashSet<>();
        log.info("알림 발생 시점에서 map 개 수 : " + CLIENTS.size());

        log.info("초대 받는 사람 객체 : " + userIdList);

        final String message = user.getUserId() + "님으로부터 " + messageType + " 알림이 도착했습니다.";

        userIdList.forEach((userId) -> {
            System.out.println("connected user 찍어보기 " + CONNECTED_USERS);
            System.out.println("userId 찍어보기 " + userId);
            if (!CONNECTED_USERS.containsKey(userId)) { // 연결되지 않았는데 알림을 보내라고 하는 경우
                log.info(userId + "는 연결되지 않았으므로 알림을 보내지 않는다."); // 현재 로그인 상태가 아니라는 것.
                return;
            }

            String uuid = CONNECTED_USERS.get(userId);
            try {
                SseEmitter emitter = CLIENTS.get(uuid);
                emitter.send(message, MediaType.APPLICATION_JSON);
                log.info("알림 클라이언트로 보냄");
            } catch (Exception e) {
                deadUuids.add(uuid);
                deadUserIds.add(userId);
                log.error("* 에러가 발생해서 삭제 목록에 추가함 *");
            }
        });

        deadUuids.forEach(CLIENTS::remove); // 문제가 발생한 채널 제거
        deadUserIds.forEach(CONNECTED_USERS::remove);
    }


}
