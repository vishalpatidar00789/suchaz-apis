import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserProfileSuchaz } from './user-profile-suchaz.model';
import { UserProfileSuchazPopupService } from './user-profile-suchaz-popup.service';
import { UserProfileSuchazService } from './user-profile-suchaz.service';

@Component({
    selector: 'jhi-user-profile-suchaz-delete-dialog',
    templateUrl: './user-profile-suchaz-delete-dialog.component.html'
})
export class UserProfileSuchazDeleteDialogComponent {

    userProfile: UserProfileSuchaz;

    constructor(
        private userProfileService: UserProfileSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userProfileListModification',
                content: 'Deleted an userProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-profile-suchaz-delete-popup',
    template: ''
})
export class UserProfileSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfilePopupService: UserProfileSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userProfilePopupService
                .open(UserProfileSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
