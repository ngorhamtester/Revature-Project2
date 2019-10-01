import { TestBed } from '@angular/core/testing';

import { GetPlaylistsService } from './get-playlists.service';

describe('GetPlaylistsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetPlaylistsService = TestBed.get(GetPlaylistsService);
    expect(service).toBeTruthy();
  });
});
