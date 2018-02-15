import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserGiftWrapperSuchaz } from './user-gift-wrapper-suchaz.model';
import { UserGiftWrapperSuchazPopupService } from './user-gift-wrapper-suchaz-popup.service';
import { UserGiftWrapperSuchazService } from './user-gift-wrapper-suchaz.service';

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz-delete-dialog',
    templateUrl: './user-gift-wrapper-suchaz-delete-dialog.component.html'
})
export class UserGiftWrapperSuchazDeleteDialogComponent {

    userGiftWrapper: UserGiftWrapperSuchaz;

    constructor(
        private userGiftWrapperService: UserGiftWrapperSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userGiftWrapperService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userGiftWrapperListModification',
                content: 'Deleted an userGiftWrapper'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz-delete-popup',
    template: ''
})
export class UserGiftWrapperSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userGiftWrapperPopupService: UserGiftWrapperSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userGiftWrapperPopupService
                .open(UserGiftWrapperSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
