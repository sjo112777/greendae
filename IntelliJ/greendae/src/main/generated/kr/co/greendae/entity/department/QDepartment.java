package kr.co.greendae.entity.department;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -1088754671L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartment department = new QDepartment("department");

    public final StringPath deptChair = createString("deptChair");

    public final StringPath deptEname = createString("deptEname");

    public final StringPath deptHp = createString("deptHp");

    public final StringPath deptName = createString("deptName");

    public final NumberPath<Integer> deptNo = createNumber("deptNo", Integer.class);

    public final StringPath deptOffice = createString("deptOffice");

    public final StringPath establishedYear = createString("establishedYear");

    public final kr.co.greendae.entity.university.QUniversity university;

    public QDepartment(String variable) {
        this(Department.class, forVariable(variable), INITS);
    }

    public QDepartment(Path<? extends Department> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartment(PathMetadata metadata, PathInits inits) {
        this(Department.class, metadata, inits);
    }

    public QDepartment(Class<? extends Department> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.university = inits.isInitialized("university") ? new kr.co.greendae.entity.university.QUniversity(forProperty("university")) : null;
    }

}

