package com.PL.PremierZone.Player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByTeamName(String teamName) {
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equalsIgnoreCase(player.getTeam())).collect(Collectors.toList());
    }
    public List<Player> getPlayersByName(String name) {
        return playerRepository.findAll().stream()
                .filter(player -> name.equalsIgnoreCase(player.getName())).collect(Collectors.toList());

    }

    public List<Player> getPlayersByPosition(String position) {
        return playerRepository.findAll().stream().filter(player -> position.equalsIgnoreCase(player.getPos()))
                        .collect(Collectors.toList());
    }
    public List<Player> getPlayersByNation(String nation){
        return playerRepository.findAll().stream().filter(player -> player.getNation().toLowerCase()
                .contains(nation.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Player> getPlayersByPositionAndTeam(String team, String position){
        return playerRepository.findAll().stream().filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }
    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player player){
        Optional<Player> existingPlayer = playerRepository.findByName(player.getName());
        if(existingPlayer.isPresent()){
           Player updatedPlayer = existingPlayer.get();
           updatedPlayer.setTeam(player.getTeam());
           updatedPlayer.setPos(player.getPos());
           updatedPlayer.setNation(player.getNation());
           updatedPlayer.setName(player.getName());
           playerRepository.save(updatedPlayer);
           return updatedPlayer;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByName(playerName);
    }

}
