import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { BuyLaterListSuchazPopupService } from './buy-later-list-suchaz-popup.service';
import { BuyLaterListSuchazService } from './buy-later-list-suchaz.service';

@Component({
    selector: 'jhi-buy-later-list-suchaz-delete-dialog',
    templateUrl: './buy-later-list-suchaz-delete-dialog.component.html'
})
export class BuyLaterListSuchazDeleteDialogComponent {

    buyLaterList: BuyLaterListSuchaz;

    constructor(
        private buyLaterListService: BuyLaterListSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.buyLaterListService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'buyLaterListListModification',
                content: 'Deleted an buyLaterList'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-buy-later-list-suchaz-delete-popup',
    template: ''
})
export class BuyLaterListSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private buyLaterListPopupService: BuyLaterListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.buyLaterListPopupService
                .open(BuyLaterListSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
