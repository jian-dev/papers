package com.diary.api.response;

import com.diary.api.db.entity.Notification;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NotificationRes {
    private Long id;
    private String notificationContent;
    private LocalDate notificationDate;
    private LocalTime notificationTime;
    private boolean notificationRead;
    private Long notInfoId;
    private String senderImageUrl;
    private String userId;
    private Long diaryId;
    private Long noteId;

    public static NotificationRes of(Notification notification) {
        NotificationRes res = new NotificationRes();
        res.id = notification.getId();
        res.notificationContent = notification.getNotificationContent();
        res.notificationDate = notification.getNotificationDate();
        res.notificationTime = notification.getNotificationTime();
        res.notificationRead = notification.isNotificationRead();
        res.notInfoId = notification.getNotificationInfo().getId();
        res.senderImageUrl = notification.getSenderImageUrl();
        res.setUserId(notification.getUser().getUserId());
        res.setDiaryId(notification.getDiaryId());
        res.setNoteId(notification.getNoteId());
        return res;
    }
}
