/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListItemSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz-delete-dialog.component';
import { WishListItemSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-item-suchaz/wish-list-item-suchaz.service';

describe('Component Tests', () => {

    describe('WishListItemSuchaz Management Delete Component', () => {
        let comp: WishListItemSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<WishListItemSuchazDeleteDialogComponent>;
        let service: WishListItemSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListItemSuchazDeleteDialogComponent],
                providers: [
                    WishListItemSuchazService
                ]
            })
            .overrideTemplate(WishListItemSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListItemSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListItemSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
