package kr.co.greendae.entity.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasic_Comment is a Querydsl query type for Basic_Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasic_Comment extends EntityPathBase<Basic_Comment> {

    private static final long serialVersionUID = -1316089278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasic_Comment basic_Comment = new QBasic_Comment("basic_Comment");

    public final kr.co.greendae.entity.article.QBasic_Article basic_article;

    public final NumberPath<Integer> cno = createNumber("cno", Integer.class);

    public final StringPath content = createString("content");

    public final StringPath regip = createString("regip");

    public final kr.co.greendae.entity.user.QUser user;

    public final StringPath wdate = createString("wdate");

    public QBasic_Comment(String variable) {
        this(Basic_Comment.class, forVariable(variable), INITS);
    }

    public QBasic_Comment(Path<? extends Basic_Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasic_Comment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasic_Comment(PathMetadata metadata, PathInits inits) {
        this(Basic_Comment.class, metadata, inits);
    }

    public QBasic_Comment(Class<? extends Basic_Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.basic_article = inits.isInitialized("basic_article") ? new kr.co.greendae.entity.article.QBasic_Article(forProperty("basic_article"), inits.get("basic_article")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.greendae.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

