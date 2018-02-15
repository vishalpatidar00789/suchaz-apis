/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz-delete-dialog.component';
import { VendorSuchazService } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.service';

describe('Component Tests', () => {

    describe('VendorSuchaz Management Delete Component', () => {
        let comp: VendorSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<VendorSuchazDeleteDialogComponent>;
        let service: VendorSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorSuchazDeleteDialogComponent],
                providers: [
                    VendorSuchazService
                ]
            })
            .overrideTemplate(VendorSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorSuchazService);
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
