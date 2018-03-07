package com.haima.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: 采集的内容
 * @Author: haima
 * @FileName: Content.java
 * @PackageName: com.haima.crm.entity
 * @E-mail: haimaclan@gmail.com
 */
public class Content implements Serializable {
	
	private Long id;            //
	private String title;       //标题
	private String subTitle;    //子标题
	private String content;     //内容
	private Date createTime;    //创建时间
	private Date updateTime;    //更新时间
	private String staticLink;  //静态连接
	private String tag;         //标签
	private String coverImg;    //封面图片
	private String smallImg;    //小图片
	private float coverWidth;   //封面图片宽度
	private float coverHeight;  //封面图片高度
	private Integer hits;       //点击数
	private Integer loves;      //喜欢数
	private Integer collections;//收藏数
	private Integer comments;   //评论数
	private String keywords;    //seo关键词
	private String description; //seo描述
	private String htmlCode;    //
	private String jsCode;      //
	private String cssCode;     //
	private Integer isCode;     //是否预览 0 不可 1可
	private String headerPic;   //头像
	private String author;      //作者名称
	private Long userId;        //用户id
	private Long channelId;     //类型1Java，2前端，3音乐 4咖啡 5茶文化 6工具
	private Integer isDelete;   //是否删除 0删除1未删除
	private Integer isTop;      //是否置顶1置顶 0未置顶
	private Integer isPush;     //是否精华推荐 0否1是
	private Integer isComment;  //是否允许评论 1允许0不允许
	private Integer issuance;   //0未发布1发布
	private Integer type;       //1文章2音乐3视频4其他
	
	public Content() {
		super();
	}

	public Content(Long id,String title,String subTitle,String content,Date createTime,Date updateTime,String staticLink,String tag,String coverImg,String smallImg,float coverWidth,float coverHeight,Integer hits,Integer loves,Integer collections,Integer comments,String keywords,String description,String htmlCode,String jsCode,String cssCode,Integer isCode,String headerPic,String author,Long userId,Long channelId,Integer isDelete,Integer isTop,Integer isPush,Integer isComment,Integer issuance,Integer type) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.staticLink = staticLink;
		this.tag = tag;
		this.coverImg = coverImg;
		this.smallImg = smallImg;
		this.coverWidth = coverWidth;
		this.coverHeight = coverHeight;
		this.hits = hits;
		this.loves = loves;
		this.collections = collections;
		this.comments = comments;
		this.keywords = keywords;
		this.description = description;
		this.htmlCode = htmlCode;
		this.jsCode = jsCode;
		this.cssCode = cssCode;
		this.isCode = isCode;
		this.headerPic = headerPic;
		this.author = author;
		this.userId = userId;
		this.channelId = channelId;
		this.isDelete = isDelete;
		this.isTop = isTop;
		this.isPush = isPush;
		this.isComment = isComment;
		this.issuance = issuance;
		this.type = type;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStaticLink() {
		return staticLink;
	}
	public void setStaticLink(String staticLink) {
		this.staticLink = staticLink;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public String getSmallImg() {
		return smallImg;
	}
	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}

	public float getCoverWidth() {
		return coverWidth;
	}
	public void setCoverWidth(float coverWidth) {
		this.coverWidth = coverWidth;
	}

	public float getCoverHeight() {
		return coverHeight;
	}
	public void setCoverHeight(float coverHeight) {
		this.coverHeight = coverHeight;
	}

	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getLoves() {
		return loves;
	}
	public void setLoves(Integer loves) {
		this.loves = loves;
	}

	public Integer getCollections() {
		return collections;
	}
	public void setCollections(Integer collections) {
		this.collections = collections;
	}

	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtmlCode() {
		return htmlCode;
	}
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}

	public String getCssCode() {
		return cssCode;
	}
	public void setCssCode(String cssCode) {
		this.cssCode = cssCode;
	}

	public Integer getIsCode() {
		return isCode;
	}
	public void setIsCode(Integer isCode) {
		this.isCode = isCode;
	}

	public String getHeaderPic() {
		return headerPic;
	}
	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsPush() {
		return isPush;
	}
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	public Integer getIssuance() {
		return issuance;
	}
	public void setIssuance(Integer issuance) {
		this.issuance = issuance;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}


}
