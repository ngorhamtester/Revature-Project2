create table sortify.users (
spotify_user_id varchar primary key,
spotify_user_uri varchar,
spotify_display_name varchar,
spotify_email varchar 
);

create table sortify.playlists (
spotify_playlist_id varchar primary key,
spotify_playlist_name varchar,
spotify_playlist_uri varchar 
);

create table sortify.user_playlist_jt (
spotify_user_id varchar,
spotify_playlist_id varchar,
foreign key (spotify_user_id) references sortify.users(spotify_user_id),
foreign key (spotify_playlist_id) references sortify.playlists(spotify_playlist_id));	