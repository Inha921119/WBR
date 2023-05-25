package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.ReplyRepository;
import com.project.demo.vo.Reply;
import com.project.demo.vo.ResultData;

@Service
public class ReplyService {
	
	private ReplyRepository replyRepository;

	@Autowired
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public List<Reply> getReplies(int loginedMemberId, int relId, String relTypeCode) {
		
		List<Reply> replies = replyRepository.getReplies(relId, relTypeCode);
		
		for (Reply reply : replies) {
			actorCanChangeData(loginedMemberId, reply);
		}
		
		return replies;
	}

	public ResultData<Integer> writeReply(int loginedMemberId, int relId, String relTypeCode, String body) {
		replyRepository.writeReply(loginedMemberId, relId, relTypeCode, body);		
		
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", "댓글 작성이 완료되었습니다.", "id", id);
	}
	
	public Reply getReply(int id) {
		return replyRepository.getReply(id);
	}

	public void deleteReply(int id) {
		replyRepository.deleteReply(id);
	}
	
	public void modifyReply(int id, String body) {
		replyRepository.modifyReply(id, body);
	}

	public void actorCanChangeData(int loginedMemberId, Reply reply) {
		ResultData actorCanChangeDataRd = actorCanMD(loginedMemberId, reply);
		reply.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}

	public ResultData actorCanMD(int loginedMemberId, Reply reply) {

		if (reply == null) {
			return ResultData.from("F-1", "해당 댓글은 존재하지 않습니다");
		}

		if (loginedMemberId != reply.getMemberId()){
			return ResultData.from("F-2", "해당 댓글에 대한 권한이 없습니다");
		}

		return ResultData.from("S-1", "가능");
	}

	public Reply getForPrintReply(int id) {
		return replyRepository.getForPrintReply(id);
	}

}
