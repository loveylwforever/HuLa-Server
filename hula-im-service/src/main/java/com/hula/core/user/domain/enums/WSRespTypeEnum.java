package com.hula.core.user.domain.enums;

import com.hula.core.chat.domain.dto.ConverseMessageDto;
import com.hula.core.chat.domain.vo.response.ChatMyRoomGroupChange;
import com.hula.core.chat.domain.vo.response.ChatRoomGroupChange;
import com.hula.core.user.domain.vo.resp.user.OffLineResp;
import com.hula.core.user.domain.vo.resp.ws.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  ws前端请求类型枚举
 * @author nyh
 */
@AllArgsConstructor
@Getter
public enum WSRespTypeEnum {
    NO_INTERNET("noInternet", "无网络连接", WSLoginUrl.class),
    LOGIN_QR_CODE("loginQrCode", "登录二维码返回", WSLoginUrl.class),
    WAITING_AUTHORIZE("waitingAuthorize", "用户扫描成功等待授权", null),
    LOGIN_SUCCESS("loginSuccess", "用户登录成功返回用户信息", WSLoginSuccess.class),
    RECEIVE_MESSAGE("receiveMessage", "新消息", WSMessage.class),
    ONLINE("online", "上线通知", WSOnlineNotify.class),
	USER_STATE_CHANGE("userStateChange", "用户状态改变", null),
	ROOM_INFO_CHANGE("roomInfoChange", "管理员修改群聊信息", ChatRoomGroupChange.class),
	MY_ROOM_INFO_CHANGE("myRoomInfoChange", "自己修改我在群里的信息", ChatMyRoomGroupChange.class),
	ROOM_GROUP_MSG("roomGroupMsg", "用户申请加群通知消息", null),
    TOKEN_EXPIRED("tokenExpired", "使前端的token失效，意味着前端需要重新登录", OffLineResp.class),
    INVALID_USER("invalidUser", "拉黑用户", WSBlack.class),
    MSG_MARK_ITEM("msgMarkItem", "消息标记", WSMsgMark.class),
    MSG_RECALL("msgRecall", "消息撤回", WSMsgRecall.class),
	VIDEO("video", "视频消息", ConverseMessageDto.class),
	PHONE("phone", "语音消息", null),
	ROOM_GROUP_NOTICE_MSG("roomGroupNoticeMsg", "群公告消息", null),
	ROOM_EDIT_GROUP_NOTICE_MSG("roomEditGroupNoticeMsg", "编辑群公告消息", null),
	ROOM_GROUP_NOTICE_READ_MSG("roomGroupNoticeReadMsg", "群公告已读", null),
	FEED_SEND_MSG("feedSendMsg", "朋友圈发布", null),
	ROOM_DISSOLUTION("roomDissolution", "群解散", null),
	ROOM_NOTIFICATION("roomNotification", "会话消息接收类型改变", null),
	SHIELD("shield", "你已屏蔽好友的消息", null),
	UNBLOCK("unblock", "你已解除屏蔽好友的消息", null),
    REQUEST_NEW_FRIEND("requestNewFriend", "好友申请", WSFriendApply.class),
    NEW_FRIEND_SESSION("newFriendSession", "成员变动", WSMemberChange.class),
    OFFLINE("offline", "下线通知", WSOfflineNotify.class),
    REQUEST_APPROVAL_FRIEND("requestApprovalFriend", "同意好友请求", WSFriendApproval.class),
    ;

    private final String type;
    private final String desc;
    private final Class<?> dataClass;

    private static Map<String, WSRespTypeEnum> cache;

    static {
        cache = Arrays.stream(WSRespTypeEnum.values()).collect(Collectors.toMap(WSRespTypeEnum::getType, Function.identity()));
    }

    public static WSRespTypeEnum of(String type) {
        return cache.get(type);
    }
}
