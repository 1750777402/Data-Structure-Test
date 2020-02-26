# SDK大方会模式接入文档

<a name="14dccc20"></a>
## AliRTCSDK大方会模式接入说明

<a name="1b708266"></a>
### 老师端

1.**joinChannel之前**设置频道模式为直播模式

```
[self.engine setChannelProfile:AliRtcChannelProfileInteractivelive];
```

2.joinChannel之前设置为**非自动推流和自动拉流模式**

```
[self.engine setAutoPublish:NO withAutoSubscribe:YES];
```

3.设置为直播模式后，切换角色为AliRtcClientRoleInteractive(入会前后均可，但是需要在**publish推流前**)

```
[self.engine setClientRole:AliRtcClientRoleInteractive];
```

**注：此接口暂时不要处理接口返回值**

4.切换成功后可收到回调

```
- (void)onUpdateRoleNotifyWithOldRole:(AliRtcClientRole)oldRole newRole:(AliRtcClientRole)newRole;
```

- 备注 ：需要业务侧关注一下当前的角色参数值，例如当前是AliRtcClientRoleInteractive角色，再次调用`[self.engine setClientRole:AliRtcClientRoleInteractive];` 不会收到回调。建议业务侧将角色切换和推拉流的逻辑区分开

5.调用publish接口开始推流

- 备注 ： publish前需要保障已经joinChannel成功，否则joinChannel会失败

- 备注 ： AliRtcClientRole 参数释义

> AliRtcClientRoleInteractive ： 参与互动角色 可以推拉流


> AliRtcClientRolelive ： 仅观看角色 只能拉流，适用于普通观众


---


6.远端用户推流、停止推流消息回调（学生端收到远端用户推流消息的回调与此相同）

```
/**
 * @brief 当远端用户的流发生变化时，返回这个消息
 * @note 远方用户停止推流，也会发送这个消息
 */
- (void)onRemoteTrackAvailableNotify:(NSString *)uid audioTrack:(AliRtcAudioTrack)audioTrack videoTrack:(AliRtcVideoTrack)videoTrack;
```

a. audioTrack = AliRtcAudioTrackNo 并且 videoTrack = AliRtcVideoTrackNo 时代表远端用户停止推流<br />b. audioTrack或者videoTrack为非No的参数值时代表远端用户对应的推流状态
<a name="ae7d4a6e"></a>
### 学生端

**joinChannel之前**设置频道模式为直播模式

```
[self.engine setChannelProfile:AliRtcChannelProfileInteractivelive];
```

<a name="3654ac1d"></a>
#### 1. 普通观众

设置为直播模式后，默认role为只观看角色，也就是只可拉流观看直播，不能推流。此角色适用于普通观众，无需其他操作

<a name="6750310a"></a>
#### 2. 连麦观众

<a name="853a819e"></a>
##### 一、 开始上麦

1.如果普通观众需要上麦切换为连麦观众的话，**需要先切换用户角色**

2.切换角色为AliRtcClientRoleInteractive(需要在**publish推流前**)

```
[self.engine setClientRole:AliRtcClientRoleInteractive];
```

3.切换成功后可收到回调

```
- (void)onUpdateRoleNotifyWithOldRole:(AliRtcClientRole)oldRole newRole:(AliRtcClientRole)newRole;
```

4.收到回调后，调用publish接口开始推流

```
[self.engine configLocalAudioPublish:YES];
[self.engine configLocalCameraPublish:YES];
[self.engine publish:^(int err) {

}];
```

<a name="1a3ec1df"></a>
##### 二、下麦

与上麦逻辑相反，如果连麦观众需要下麦切换为普通观众的话，**需要先停止推流，在进行角色切换**

1.停止推流

```
[self.engine configLocalAudioPublish:NO];
[self.engine configLocalCameraPublish:NO];
[self.engine publish:^(int err) {

}];
```

2.停止推流收到回调之后，即可切换为普通观众模式

```
[self.engine setClientRole:AliRtcClientRolelive];
```

3.切换成功后可收到回调

```
- (void)onUpdateRoleNotifyWithOldRole:(AliRtcClientRole)oldRole newRole:(AliRtcClientRole)newRole;
```

<a name="bdhfv"></a>
### 常见问题
<a name="5JYQp"></a>
#### 1. 观众端（学生端）处理
<a name="FqmFE"></a>
#### 2. 主播端（老师端）处理
<a name="Uo5Fi"></a>
#### 3. publish调用时机
<a name="FGMWN"></a>
#### 4. setClientRole调用时机
