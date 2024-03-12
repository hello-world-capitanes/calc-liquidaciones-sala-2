package com.babelgroup.repositories.policy;

import com.babelgroup.model.Policy;

public interface IPolicyRepository {

    Policy findPolicyById(String id);
}
