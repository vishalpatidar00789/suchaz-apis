import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BuyLaterListItemSuchaz } from './buy-later-list-item-suchaz.model';
import { BuyLaterListItemSuchazPopupService } from './buy-later-list-item-suchaz-popup.service';
import { BuyLaterListItemSuchazService } from './buy-later-list-item-suchaz.service';

@Component({
    selector: 'jhi-buy-later-list-item-suchaz-delete-dialog',
    templateUrl: './buy-later-list-item-suchaz-delete-dialog.component.html'
})
export class BuyLaterListItemSuchazDeleteDialogComponent {

    buyLaterListItem: BuyLaterListItemSuchaz;

    constructor(
        private buyLaterListItemService: BuyLaterListItemSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.buyLaterListItemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'buyLaterListItemListModification',
                content: 'Deleted an buyLaterListItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-buy-later-list-item-suchaz-delete-popup',
    template: ''
})
export class BuyLaterListItemSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private buyLaterListItemPopupService: BuyLaterListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.buyLaterListItemPopupService
                .open(BuyLaterListItemSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
