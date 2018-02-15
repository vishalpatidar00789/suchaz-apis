import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { GiftWrapperSuchazPopupService } from './gift-wrapper-suchaz-popup.service';
import { GiftWrapperSuchazService } from './gift-wrapper-suchaz.service';

@Component({
    selector: 'jhi-gift-wrapper-suchaz-delete-dialog',
    templateUrl: './gift-wrapper-suchaz-delete-dialog.component.html'
})
export class GiftWrapperSuchazDeleteDialogComponent {

    giftWrapper: GiftWrapperSuchaz;

    constructor(
        private giftWrapperService: GiftWrapperSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.giftWrapperService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'giftWrapperListModification',
                content: 'Deleted an giftWrapper'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-gift-wrapper-suchaz-delete-popup',
    template: ''
})
export class GiftWrapperSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giftWrapperPopupService: GiftWrapperSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.giftWrapperPopupService
                .open(GiftWrapperSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
