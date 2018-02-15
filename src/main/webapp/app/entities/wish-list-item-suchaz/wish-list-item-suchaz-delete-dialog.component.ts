import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { WishListItemSuchazPopupService } from './wish-list-item-suchaz-popup.service';
import { WishListItemSuchazService } from './wish-list-item-suchaz.service';

@Component({
    selector: 'jhi-wish-list-item-suchaz-delete-dialog',
    templateUrl: './wish-list-item-suchaz-delete-dialog.component.html'
})
export class WishListItemSuchazDeleteDialogComponent {

    wishListItem: WishListItemSuchaz;

    constructor(
        private wishListItemService: WishListItemSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.wishListItemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'wishListItemListModification',
                content: 'Deleted an wishListItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-wish-list-item-suchaz-delete-popup',
    template: ''
})
export class WishListItemSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListItemPopupService: WishListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.wishListItemPopupService
                .open(WishListItemSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
