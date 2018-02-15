import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SuchAzUserSuchaz } from './such-az-user-suchaz.model';
import { SuchAzUserSuchazPopupService } from './such-az-user-suchaz-popup.service';
import { SuchAzUserSuchazService } from './such-az-user-suchaz.service';

@Component({
    selector: 'jhi-such-az-user-suchaz-delete-dialog',
    templateUrl: './such-az-user-suchaz-delete-dialog.component.html'
})
export class SuchAzUserSuchazDeleteDialogComponent {

    suchAzUser: SuchAzUserSuchaz;

    constructor(
        private suchAzUserService: SuchAzUserSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.suchAzUserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'suchAzUserListModification',
                content: 'Deleted an suchAzUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-such-az-user-suchaz-delete-popup',
    template: ''
})
export class SuchAzUserSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private suchAzUserPopupService: SuchAzUserSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.suchAzUserPopupService
                .open(SuchAzUserSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
