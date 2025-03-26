package kr.co.greendae.entity.file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasic_File is a Querydsl query type for Basic_File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasic_File extends EntityPathBase<Basic_File> {

    private static final long serialVersionUID = -272961118L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasic_File basic_File = new QBasic_File("basic_File");

    public final kr.co.greendae.entity.article.QBasic_Article basic_article;

    public final NumberPath<Integer> download = createNumber("download", Integer.class);

    public final NumberPath<Integer> fno = createNumber("fno", Integer.class);

    public final StringPath oName = createString("oName");

    public final StringPath rdate = createString("rdate");

    public final StringPath sName = createString("sName");

    public QBasic_File(String variable) {
        this(Basic_File.class, forVariable(variable), INITS);
    }

    public QBasic_File(Path<? extends Basic_File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasic_File(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasic_File(PathMetadata metadata, PathInits inits) {
        this(Basic_File.class, metadata, inits);
    }

    public QBasic_File(Class<? extends Basic_File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.basic_article = inits.isInitialized("basic_article") ? new kr.co.greendae.entity.article.QBasic_Article(forProperty("basic_article"), inits.get("basic_article")) : null;
    }

}

