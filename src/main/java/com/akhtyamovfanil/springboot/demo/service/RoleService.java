package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.model.Role;
import com.akhtyamovfanil.springboot.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @PersistenceContext
    EntityManager entityManager;

    public void save(Role role) {

        Role managed = entityManager.merge(role);
        entityManager.persist(managed);
    }


    public void delete(Role role) {
        roleRepository.delete(role);

    }


    public Role getById(Long id) {
        return roleRepository.getById(id);

    }


    public Role getRoleByName(String rolename) {
        return roleRepository.getRoleByName(rolename);

    }

    public Set<Role> getRoleSet(){
        return roleRepository.getRoleSet();
    }



    public Set<Role> getRoleSetForUser(String[] rolenames) {
        Set<Role> rolesSet = new HashSet<>();
        for (Role role : getRoleSet()) {
            for (String st : rolenames) {
                if (st.equals(role.getName())) {
                    rolesSet.add(getRoleByName(role.getName()));
                }
            }
        }
        return rolesSet;
    }

}
