package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.SendRequest;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.SendRequestRepository;
import io.github.magicolala.reseausocial.repository.UserRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SendRequestServiceImpl implements SendRequestService {

    private final UserUtil              userUtil;
    private final UserRepository        userRepository;
    private final SendRequestRepository sendRequestRepository;

    @Override
    public SendRequest save(long id) throws Exception {
        User           user   = userUtil.getCurrentUser();
        Optional<User> friend = userRepository.findById(id);

        if (friend.isPresent()) {
            User        _friend     = friend.get();
            SendRequest sendRequest = new SendRequest();
            sendRequest.setUser(user);
            sendRequest.setFriend(_friend);
            sendRequest.setState(SendRequest.State.WAITING);

            return sendRequestRepository.save(sendRequest);
        } else {
            throw new Exception("Aucun ami avec cet id");
        }
    }

    @Override
    public SendRequest accept(long id, String isAccept) {
        Optional<SendRequest> sendRequest = sendRequestRepository.findById(id);

        if (sendRequest.isPresent()) {
            SendRequest _sendRequest = sendRequest.get();

            if (Objects.equals(isAccept, "accept"))
                _sendRequest.setState(SendRequest.State.ACCEPTED);
            else
                _sendRequest.setState(SendRequest.State.REFUSED);

            return sendRequestRepository.save(_sendRequest);
        }

        return null;
    }

}
