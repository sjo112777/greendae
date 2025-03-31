package kr.co.greendae.entity.department;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChairperson is a Querydsl query type for Chairperson
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChairperson extends EntityPathBase<Chairperson> {

    private static final long serialVersionUID = -201131653L;

    public static final QChairperson chairperson = new QChairperson("chairperson");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public QChairperson(String variable) {
        super(Chairperson.class, forVariable(variable));
    }

    public QChairperson(Path<? extends Chairperson> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChairperson(PathMetadata metadata) {
        super(Chairperson.class, metadata);
    }

}

