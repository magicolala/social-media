package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.SendMessage;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.SendMessageRepository;
import io.github.magicolala.reseausocial.repository.UserRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    private final UserUtil              userUtil;
    private final UserRepository        userRepository;
    private final SendMessageRepository sendMessageRepository;

    @Override
    public SendMessage save(SendMessage message, long idUser) {
        Optional<User> _recipient = userRepository.findById(idUser);

        if (_recipient.isPresent()) {
            User recipient   = _recipient.get();
            User transmitter = userUtil.getCurrentUser();

            message.setRecipient(recipient);
            message.setTransmitter(transmitter);

            return sendMessageRepository.save(message);
        }

        return null;
    }

}
