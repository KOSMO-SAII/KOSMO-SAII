package com.japc.service;

import com.japc.dto.MemoDTO;
import com.japc.entity.Memo;
import com.japc.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public Memo saveMemo(Memo memo){
        return memoRepository.save(memo);

    }
}
