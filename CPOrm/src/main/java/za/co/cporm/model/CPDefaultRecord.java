package za.co.cporm.model;

import android.content.Context;
import za.co.cporm.model.annotation.Column.Column;
import za.co.cporm.model.annotation.Column.PrimaryKey;

/**
 * Basic Content Provider Record implementation that contains a id field
 * that will be android list view compatible, and some helper methods.
 */
public abstract class CPDefaultRecord<T> extends CPRecord<T> {

    @Column(columnName = "_id")
    @PrimaryKey(autoIncrement = true)
    protected Long _id;

    /**
     * Finds a record based on the id column
     * @param context Current context
     * @param id the id of the record to find
     * @return The record, if found.
     */
    public static <T> T findById(Context context, Class<T> object, long id){
        return CPHelper.findByPrimaryKey(context, object, id);
    }

    /**
     * Checks if this record has an id, if the id is present this record will be updated,
     * if it is null, it will be inserted instead, and the inserted id assigned to this one.
     * @param context The context used to save the record.
     */
    public void save(Context context){

        if(_id == null) {

            _id = CPHelper.insertAndReturn(context, this)._id;
        }
        else CPHelper.update(context, this);
    }

    public Long getId() {
        return _id;
    }
}
