package pl.wsb.students.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import pl.wsb.students.exceptions.ValidationException;
import pl.wsb.students.hibernatemodel.UserAccount;
import pl.wsb.students.model.LogOutUserRequest;
import pl.wsb.students.model.RegisterUserRequest;
import pl.wsb.students.model.UpdateUserRequest;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class UserAccountRepository extends AbstractRepository<UserAccount, Integer> {
    @Override
    protected Class<UserAccount> getPersistentClass() {
        return UserAccount.class;
    }

    public UserAccount findByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder =
                EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<UserAccount> criteriaQuery =
                criteriaBuilder.createQuery(UserAccount.class);
        Root<UserAccount> userAccounts = criteriaQuery.from(UserAccount.class);
        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                userAccounts.<String>get("email")
                        ),
                        email.toLowerCase())
        );
        return getFirstResultOrNull(
                EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList()
        );
    }

    //tworzenie nowego usera
    public UserAccount registerUser(RegisterUserRequest userRequest) throws ValidationException {
        if (userRequest == null) {
            throw new ValidationException("userRequest");
        } //if
        userRequest.validateData();
        userRequest.passwordIsValid();
        UserAccount userAccount = findByEmail(userRequest.getEmail());
        if (userAccount != null) {
            throw new ValidationException("Provided email already taken...");
        } //if
        userAccount = new UserAccount();
        userAccount.setCreated(new Date());
        userAccount.setModified(new Date());
        userAccount.setEmail(userRequest.getEmail());
        userAccount.setPassSalt(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(256)
                )
        );
        userAccount.setPassHash(
                userAccount.generatePassHash(
                        userRequest.getPassword(),
                        userAccount.getPassSalt()
                )
        );
        EntityManagerHelper.startTransaction();
        userAccount = merge(userAccount);
        EntityManagerHelper.commitTransaction();
        UserAccountRoleRepository userAccountRoleRepository = new UserAccountRoleRepository();
        userAccountRoleRepository.assignUserToRole(userAccount, RoleRepository.findByAbbr("USER"));
        return userAccount;
    }

    //edytowanie usera
    //*********************************************************************************************************************
    public UserAccount editUser(UpdateUserRequest editUser) throws ValidationException {
        if (editUser == null) {
            throw new ValidationException("userRequest");
        } //if
        editUser.validateData();
        UserAccount userAccount = new UserAccount();
        userAccount.setModified(new Date());
        userAccount.getPassHash();
        userAccount.getPassSalt();
        userAccount.setEmail(editUser.getEmail());
        userAccount.setDeleted(1);
        EntityManagerHelper.startTransaction();
        userAccount = merge(userAccount);
        EntityManagerHelper.commitTransaction();
        return userAccount;
    }

    //wylogowanie usera
    //*********************************************************************************************************************
    public UserAccount logoutUser(LogOutUserRequest logoutUser) throws ValidationException {
        if (logoutUser == null) {
            throw new ValidationException("logoutRequest");
        } //if
        logoutUser.validateData();
        UserAccount userAccount = null;
        userAccount.setModified(new Date());
        userAccount.getPassHash();
        userAccount.getPassSalt();


        return userAccount;
    }
}