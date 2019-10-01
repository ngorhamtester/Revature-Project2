import { TestBed } from '@angular/core/testing';

import { MergePlaylistsService } from './merge-playlists.service';

describe('MergePlaylistsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MergePlaylistsService = TestBed.get(MergePlaylistsService);
    expect(service).toBeTruthy();
  });
});
