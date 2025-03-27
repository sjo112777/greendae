package kr.co.greendae.entity.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasic_Article is a Querydsl query type for Basic_Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasic_Article extends EntityPathBase<Basic_Article> {

    private static final long serialVersionUID = -805110992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasic_Article basic_Article = new QBasic_Article("basic_Article");

    public final ListPath<kr.co.greendae.entity.comment.Basic_Comment, kr.co.greendae.entity.comment.QBasic_Comment> basic_comment = this.<kr.co.greendae.entity.comment.Basic_Comment, kr.co.greendae.entity.comment.QBasic_Comment>createList("basic_comment", kr.co.greendae.entity.comment.Basic_Comment.class, kr.co.greendae.entity.comment.QBasic_Comment.class, PathInits.DIRECT2);

    public final ListPath<kr.co.greendae.entity.file.Basic_File, kr.co.greendae.entity.file.QBasic_File> basic_file = this.<kr.co.greendae.entity.file.Basic_File, kr.co.greendae.entity.file.QBasic_File>createList("basic_file", kr.co.greendae.entity.file.Basic_File.class, kr.co.greendae.entity.file.QBasic_File.class, PathInits.DIRECT2);

    public final StringPath cate = createString("cate");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath regip = createString("regip");

    public final StringPath title = createString("title");

    public final kr.co.greendae.entity.user.QUser user;

    public final StringPath wdate = createString("wdate");

    public QBasic_Article(String variable) {
        this(Basic_Article.class, forVariable(variable), INITS);
    }

    public QBasic_Article(Path<? extends Basic_Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasic_Article(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasic_Article(PathMetadata metadata, PathInits inits) {
        this(Basic_Article.class, metadata, inits);
    }

    public QBasic_Article(Class<? extends Basic_Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.greendae.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

