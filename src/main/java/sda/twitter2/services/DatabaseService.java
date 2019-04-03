package sda.twitter2.services;

public interface DatabaseService<R> {

    boolean create (R r);
    boolean update (R... r);
    boolean isExist(R r);
    R find (R r);
    R findById (Long id);
    boolean delete (R r);

}
