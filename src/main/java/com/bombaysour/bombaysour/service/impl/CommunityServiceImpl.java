package com.bombaysour.bombaysour.service.impl;

import com.bombaysour.bombaysour.model.Community;
import com.bombaysour.bombaysour.repository.CommunityRepository;
import com.bombaysour.bombaysour.service.CommunityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bombaysour.bombaysour.service.utils.Validation.checkId;
import static com.bombaysour.bombaysour.service.utils.Validation.checkObjectExistsById;
import static com.bombaysour.bombaysour.service.utils.Validation.checkSave;

@Service
public class CommunityServiceImpl implements CommunityService {

    private static final Logger LOGGER = Logger.getLogger(CommunityServiceImpl.class);

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public Community findOneAvailable(Long id) {
        checkId(id);
        return communityRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Community> findAllAvailable() {
        return communityRepository.findAllByAvailable(true);
    }

    @Override
    public Community findOne(Long id) {
        checkId(id);
        return communityRepository.findOne(id);
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Community save(Community community) {
        checkSave(community);
        LOGGER.info("----COMMUNITY--SAVE----");
        LOGGER.info(community);
        LOGGER.info("-----------------------");
        Community communitySave = communityRepository.save(community
                .setAvailable(true));
        return communitySave;
    }

    @Override
    public Community update(Community community) {
        checkObjectExistsById(community.getId(), communityRepository);
        LOGGER.info("----COMMUNITY--UPDATE----");
        LOGGER.info(community);
        LOGGER.info("-------------------------");
        return communityRepository.save(findOne(community.getId())
                .setArticleTitle(community.getArticleTitle())
                .setText(community.getText())
                .setAvailable(community.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            communityRepository.delete(checkObjectExistsById(id, communityRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
