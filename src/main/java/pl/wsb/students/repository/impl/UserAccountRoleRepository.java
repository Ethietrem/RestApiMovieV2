package pl.wsb.students.repository.impl;

import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.Role;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.hibernatemodel.UserAccountRole;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import java.util.Date;

public class UserAccountRoleRepository extends AbstractRepository<UserAccountRole,
        Integer> {
    @Override
    protected Class<UserAccountRole> getPersistentClass() {
        return UserAccountRole.class;
    }

    //przydzielenie usera do roli
    public void assignUserToRole(UserAccount userAccount, Role role) throws
            ValidationException {
        if (role == null) {
            throw new ValidationException("role");
        } //if
        if (userAccount == null) {
            throw new ValidationException("userAccount");
        } //if
        UserAccountRole userAccountRole = new UserAccountRole();
        userAccountRole.setCreated(new Date());
        userAccountRole.setModified(new Date());
        userAccountRole.setRole(role);
        userAccountRole.setUserAccount(userAccount);
        EntityManagerHelper.startTransaction();
        merge(userAccountRole);
        EntityManagerHelper.commitTransaction();
    }

}